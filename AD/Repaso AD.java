String datosUsuario = nombre + "$" + apellidos + "$" + email;

//********************************** ESCRITURA EN ARCHIVO *************************************/
//Creamos un objeto FileWriter que referencia al archivo "datosContacto.dat" y que lo crea
//si no existe, y si existe permite que se añadan datos al final del mismo como
//archivo secuencial.
FileWriter fileWriter = new FileWriter("datosContacto.dat", true);

//BufferedWriter es una clase en Java que proporciona un búfer para escribir texto en un flujo de 
//salida de caracteres. BuffererdWriter se asociará con el FileWriter y aprovechará sus capacidades 
//de escritura, pero con el beneficio adicional de un búfer, que puede mejorar el rendimiento al 
//agrupar escrituras en bloques más grandes antes de enviarlas al sistema de archivos.  
try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
    bufferedWriter.write(datosUsuario);
    bufferedWriter.newLine();
} catch (IOException e) {
    System.out.println(e.getMessage());
}
fileWriter.close();


/********************************** LECTURA DE ARCHIVO **********************************/
//Creamos un objeto FileReader que se conecta a un archivo específico en el sistema
//de archivos. El constructor de FileReader toma un objeto File o una cadena que representa 
//la ruta del archivo como parámetro.
//FileReader se utiliza para leer caracteres de un archivo.
FileReader fileReader = new FileReader(archivo);
int contadorRegistros;

//BufferedReader se utiliza para mejorar la eficiencia y proporcionar la capacidad 
//de leer líneas completas en lugar de caracteres individuales. 
try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
    String linea;
    contadorRegistros = 0;
    System.out.println("Datos de usuarios:");
    while ((linea = bufferedReader.readLine()) != null) {
        String[] campos = linea.split("\\$");
        
        if (campos.length == 3) {
            String nombre = campos[0];
            String apellidos = campos[1];
            String email = campos[2];
            
            System.out.println("Nombre: " + nombre);
            System.out.println("Apellidos: " + apellidos);
            System.out.println("Email: " + email);
            System.out.println();
            
            contadorRegistros++;
        }
    }
}catch (IOException e) {
    System.out.println(e.getMessage());
}
fileReader.close();

/******************************** CONEXION A BASE DE DATOS ******************************/
String url = "jdbc:mysql://localhost:3307/centroseducacioninfantil";
String user = "root";
String password = "admin";
Connection con = null;
con = DriverManager.getConnection(url, user, password);

/********************************** CONSULTAS CON JDBC ************************************/
try {
    // preparación de consulta
    String consulta = "SELECT g.nombre, COUNT(e.nombre) total "
            + "FROM guarderia g, educador_infantil e "
            + "WHERE e.codigo_guarderia=g.codigo "
            + "GROUP BY e.codigo_guarderia;";
    PreparedStatement pstmt;
    pstmt = con.prepareStatement(consulta);

    // Se ejecuta la consulta
    ResultSet rs = pstmt.executeQuery();

    // Se imprimen resultados recorriendo el objeto ResultSet
    System.out.println("\nGUARDERÍA              NÚMERO DE EDUCADORES"); 
    while (rs.next()) {
        String nombreGuarderia = rs.getString("nombre");
        int cantidad = rs.getInt("total");
        System.out.printf("%-25s", nombreGuarderia);
        System.out.printf("%-25s", cantidad);
        System.out.println("");
    }
    // liberamos recursos
    rs.close();
    pstmt.close();
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}

/************************************** CONSULTA PARAMETRIZADA ***********************************/
try {
    // preparación de la consulta
    String consulta = "SELECT SUM(salario) total "
            + "FROM educador_infantil "
            + "WHERE codigo_guarderia=?";
    PreparedStatement pstmt = con.prepareStatement(consulta);
    
    System.out.println("Las guarderías disponibles son:");
    //Consulta para listar los códigos y nombres de las guarderías
    try (Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT codigo, nombre FROM guarderia");){
        while (rs.next()) {
            System.out.println(" * " + rs.getString("codigo")+" "+rs.getString("nombre"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    // Se solicita el código de una guardería
    System.out.println("Introduce código de guardería:");
    Scanner sc = new Scanner(System.in);
    String codigoG = sc.nextLine();

    // Se le asigna valor al parámetro con el dato introducido por teclado y se ejecuta la consulta
    pstmt.setString(1, codigoG);
    ResultSet rs = pstmt.executeQuery();

    // Se imprimen resultados recorriendo el objeto ResultSet
    System.out.print("El gasto en salarios de los educadores de la guarderia " + codigoG + " es: ");
    while (rs.next()) {
        int totalSalarios = rs.getInt("total");
        System.out.println(totalSalarios + " €\n");
    }

    // liberamos recursos
    rs.close();
    pstmt.close();  
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}

/*********************** EJECUCION DE PROCEDIMIENTO *********************/
try {
    // Se solicitan los valores del intervalo de los salarios
    Scanner sc = new Scanner(System.in);
    System.out.println(">>Introduzca el valor mínimo de salario:");
    int salMin = Integer.parseInt(sc.nextLine());
    System.out.println(">>Introduzca el valor máximo de salario:");
    int salMax = Integer.parseInt(sc.nextLine());

    try ( // Se prepara la llamada al procedimiento
          CallableStatement prc = con.prepareCall("{ call Proc2(?, ?, ?) }")) {
        // Se le da valor a los parámetros de entrada "v_minimo" y "v_maximo" con los valores introducidos por el usuario
        prc.setInt("v_minimo", salMin);
        prc.setInt("v_maximo", salMax);
        // Se ejecuta el procedimiento
        prc.executeQuery();
        // Se imprimen el resultado que está disponible en el parámetro de salida "numEducadores"
        System.out.println("El número de educadores con salario comprendido entre " + salMin + "€ y "+salMax +"€ es de: " + prc.getInt("numEducadores") + "\n");
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}

/*********************** TRANSACCION EN JDBC ***************************************************/
String url = "jdbc:mysql://localhost:3306/mi_base_de_datos";
String user = "root";
String password = "1234";

Connection connection = null;

try {
    // 1. Establecer conexión
    connection = DriverManager.getConnection(url, user, password);

    // 2. Deshabilitar auto-commit para manejar transacciones manualmente
    connection.setAutoCommit(false);

    // 3. Preparar las sentencias SQL
    String sql1 = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
    String sql2 = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

    try (
        PreparedStatement stmt1 = connection.prepareStatement(sql1);
        PreparedStatement stmt2 = connection.prepareStatement(sql2)
    ) {
        // 4. Ejecutar la primera operación
        stmt1.setDouble(1, 500); // Monto a transferir
        stmt1.setInt(2, 1);      // ID de la cuenta origen
        stmt1.executeUpdate();

        // 5. Ejecutar la segunda operación
        stmt2.setDouble(1, 500); // Monto a transferir
        stmt2.setInt(2, 2);      // ID de la cuenta destino
        stmt2.executeUpdate();

        // 6. Confirmar la transacción
        connection.commit();
        System.out.println("Transacción completada con éxito.");
    } catch (SQLException e) {
        // 7. Revertir en caso de error
        if (connection != null) {
            connection.rollback();
            System.out.println("Transacción revertida debido a un error: " + e.getMessage());
        }
    }

} catch (SQLException e) {
    e.printStackTrace();
} finally {
    // 8. Cerrar la conexión
    if (connection != null) {
        try {
            connection.setAutoCommit(true); // Restaurar el modo de auto-commit
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}