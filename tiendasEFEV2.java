import java.util.Scanner;
import java.util.regex.Pattern;

public class tiendasEFEV2 {
    // Variables para autenticación y registro
    private static String emailGuardado, passwordGuardado;
    private static String nombreGuardado, apellidosGuardados, dniGuardado, celularGuardado;
    static String productoCarrito = "";
    static double precioCarrito = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n--- BIENVENIDO A TIENDAS EFE ---");
            System.out.println("1. Registro de Usuario");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Ver Productos Disponibles");
            System.out.println("4. Buscar Productos");
            System.out.println("5. Agregar al Carrito");
            System.out.println("6. Ver Carrito");
            System.out.println("7. Procesar Pedido");
            System.out.println("8. Elegir Envío");
            System.out.println("9. Seleccionar Método de Pago");
            System.out.println("10. Generar Comprobante");
            System.out.println("11. Salir del Sistema");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion) {
                case 1: registrarUsuario(); break;
                case 2: if (iniciarSesion()) System.out.println("Inicio de sesión exitoso."); break;
                case 3: mostrarProductos(); break;
                case 4: buscarProducto(); break;
                case 5: agregarCarrito(); break;
                case 6: verCarrito(); break;
                case 7: procesarPedido(); break;
                case 8: elegirEnvio(); break;
                case 9: metodoPago(); break;
                case 10: generarComprobante(); break;
                case 11: System.out.println("Gracias por visitar Tiendas EFE. ¡Hasta pronto!"); break;
                default: System.out.println("Opción inválida.");
            }
        } while(opcion != 11);
    }

    // Registro de Usuario con validaciones
    public static void registrarUsuario() {
        System.out.println("\n--- Registro de Usuario ---");

        System.out.println("Ingrese su nombre:");
        nombreGuardado = sc.nextLine();

        System.out.println("Ingrese sus apellidos:");
        apellidosGuardados = sc.nextLine();

        do {
            System.out.println("Ingrese su DNI:");
            dniGuardado = sc.nextLine();
            if (!validarDNI(dniGuardado)) {
                System.out.println("❌ DNI inválido. Debe contener exactamente 8 dígitos.");
            }
        } while (!validarDNI(dniGuardado));

        do {
            System.out.println("Ingrese su celular:");
            celularGuardado = sc.nextLine();
            if (!validarCelular(celularGuardado)) {
                System.out.println("❌ Celular inválido. Debe contener exactamente 9 dígitos.");
            }
        } while (!validarCelular(celularGuardado));

        do {
            System.out.println("Ingrese su correo electrónico:");
            emailGuardado = sc.nextLine();
            if (!validarCorreo(emailGuardado)) {
                System.out.println("❌ Correo inválido. Debe contener '@' y terminar en '.com'");
            }
        } while (!validarCorreo(emailGuardado));

        System.out.println("Ingrese su contraseña:");
        passwordGuardado = sc.nextLine();

        System.out.println("✅ Registro exitoso. ¡Bienvenido a Tiendas EFE!");
    }

    // Inicio de sesión
    public static boolean iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");

        while (true) {
            System.out.println("Ingrese su correo:");
            String emailIngresado = sc.nextLine();
            System.out.println("Ingrese su password:");
            String passwordIngresado = sc.nextLine();

            if (emailIngresado.equals(emailGuardado) && passwordIngresado.equals(passwordGuardado)) {
                return true;
            } else {
                System.out.println("❌ Correo o contraseña incorrectos. Intente nuevamente.");
            }
        }
    }

    // Métodos de validación
    private static boolean validarCorreo(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.com$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    private static boolean validarDNI(String dni) {
        return dni.matches("\\d{8}");
    }

    private static boolean validarCelular(String celular) {
        return celular.matches("\\d{9}");
    }

    // 4. Visualizacion de Productos
    static void mostrarProductos() {
        System.out.println("\n--- Productos Disponibles (LAVADORAS) ---");
        System.out.println("Marca: LG | Modelo: TwinWash | Capacidad: 22kg | Precio: S/. 3,299");
        System.out.println("Marca: Samsung | Modelo: Ecobubble | Capacidad: 20kg | Precio: S/. 2,899");
        System.out.println("Marca: Mabe | Modelo: AquaSaver | Capacidad: 19kg | Precio: S/. 1,899");
    }
    // 5. Busqueda de Productos
    static void buscarProducto() {
        System.out.println("\n--- Buscar Producto ---");
        System.out.print("Ingrese marca o capacidad o precio máximo: ");
        String criterio = sc.nextLine();
        if (criterio.equalsIgnoreCase("LG") || criterio.contains("22")) {
            System.out.println("LG TwinWash - 22kg - S/. 3,299");
        }
        if (criterio.equalsIgnoreCase("Samsung") || criterio.contains("20") || criterio.contains("2800")) {
            System.out.println("Samsung Ecobubble - 20kg - S/. 2,899");
        }
        if (criterio.equalsIgnoreCase("Mabe") || criterio.contains("19") || criterio.contains("1900")) {
            System.out.println("Mabe AquaSaver - 19kg - S/. 1,899");
        }
    }
    // 6. Agregar Productos al Carrito
    static void agregarCarrito() {
        System.out.println("\n--- Agregar al Carrito ---");
        System.out.print("Ingrese el nombre del producto a agregar: ");
        productoCarrito = sc.nextLine();
        System.out.print("Ingrese su precio: S/.");
        precioCarrito = sc.nextDouble();
        System.out.println("Producto agregado al carrito.");
    }
    // 7. Ver Carrito de Compras
    static void verCarrito() {
        System.out.println("\n--- Carrito de Compras ---");
        if (!productoCarrito.equals("")) {
            System.out.println("Producto: " + productoCarrito + " | Precio: S/. " + precioCarrito);
        } else {
            System.out.println("El carrito está vacío.");
        }
    }
    // 8. Procesar Pedido y Validar Stock
    static void procesarPedido() {
        System.out.println("\n--- Procesando Pedido ---");
        if (!productoCarrito.equals("")) {
            System.out.println("Producto disponible. Pedido procesado.");
        } else {
            System.out.println("No hay productos en el carrito.");
        }
    }
    // 9. Elegir Tipo de Envio
    static void elegirEnvio() {
        System.out.println("\n--- Tipo de Envío ---");
        System.out.println("1. Envío a domicilio");
        System.out.println("2. Retiro en tienda");
        int envio = sc.nextInt();
        if (envio == 1) {
            System.out.println("Seleccionaste envío a domicilio.");
        } else {
            System.out.println("Seleccionaste retiro en tienda.");
        }
    }
    // 10. Seleccionar Metodo de Pago
    static void metodoPago() {
        System.out.println("\n--- Método de Pago ---");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. Tarjeta de débito");
        System.out.println("3. PagoEfectivo");
        System.out.println("4. Transferencia bancaria");
        int metodo = sc.nextInt();
        System.out.println("Método de pago seleccionado correctamente.");
    }
    // 11. Generar Comprobante
    static void generarComprobante() {
        System.out.println("\n--- Comprobante de Compra ---");
        if (!productoCarrito.equals("")) {
            System.out.println("Cliente: " + nombreGuardado);
            System.out.println("Producto: " + productoCarrito);
            System.out.println("Total pagado: S/." + precioCarrito);
            System.out.println("Se envió comprobante a: " + emailGuardado);
        } else {
            System.out.println("No hay productos para generar comprobante.");
        }
    }
}
