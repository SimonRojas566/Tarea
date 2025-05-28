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
//AXCEL
    // 1 Registro de Usuario con validaciones
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

    // 2 Inicio de sesión con límite de intentos
    public static boolean iniciarSesion() {
        System.out.println("\n--- Iniciar Sesión ---");
        int intentos = 0;
        final int MAX_INTENTOS = 3;

        while (intentos < MAX_INTENTOS) {
            System.out.println("Ingrese su correo:");
            String emailIngresado = sc.nextLine();
            System.out.println("Ingrese su contraseña:");
            String passwordIngresado = sc.nextLine();

            if (emailIngresado.equals(emailGuardado) && passwordIngresado.equals(passwordGuardado)) {
                System.out.println("✅ Inicio de sesión exitoso.");
                return true;
            } else {
                intentos++;
                System.out.println("❌ El usuario o contraseña ingresados son incorrectos. Revisa los datos y vuélvelos a ingresar. Intento " + intentos + " de " + MAX_INTENTOS);
            }
        }

        System.out.println("Se acabaron los intentos");
        return false;
    }


    // 3 Métodos de validación
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
// ROMULO
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
    // 6. Agregar Productos al Carrito (permitiendo múltiples productos)
    static void agregarCarrito() {
        System.out.println("\n--- Agregar al Carrito ---");
        boolean seguirComprando = true;

        while (seguirComprando) {
            System.out.println("Seleccione un producto para agregar:");
            System.out.println("1. LG TwinWash - 22kg - S/. 3,299");
            System.out.println("2. Samsung Ecobubble - 20kg - S/. 2,899");
            System.out.println("3. Mabe AquaSaver - 19kg - S/. 1,899");
            System.out.print("Ingrese el número del producto: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    productoCarrito += "LG TwinWash - 22kg\n";
                    precioCarrito += 3299;
                    break;
                case 2:
                    productoCarrito += "Samsung Ecobubble - 20kg\n";
                    precioCarrito += 2899;
                    break;
                case 3:
                    productoCarrito += "Mabe AquaSaver - 19kg\n";
                    precioCarrito += 1899;
                    break;
                default:
                    System.out.println("❌ Opción inválida.");
                    continue;
            }

            System.out.println("✅ Producto agregado al carrito.");
            System.out.print("¿Desea agregar otro producto? (Sí/No): ");
            String respuesta = sc.nextLine();
            seguirComprando = respuesta.equalsIgnoreCase("Sí");
        }

        System.out.println("🛒 Productos en carrito:\n" + productoCarrito);
        System.out.println("💰 Precio total: S/. " + precioCarrito);
    }
// KENJI

    // 7. Ver Carrito de Compras
    static void verCarrito() {
        System.out.println("\n--- Carrito de Compras ---");

        if (!productoCarrito.isEmpty()) {
            System.out.println("✅ Productos en carrito:");
            System.out.println(productoCarrito); // Muestra los productos tal como fueron almacenados

            System.out.println("💰 Precio total: S/. " + precioCarrito);
        } else {
            System.out.println("🛒 El carrito está vacío.");
        }
    }

    // 8. Procesar Pedido y Validar Stock
    static void procesarPedido() {
        System.out.println("\n--- Procesando Pedido ---");

        // Definir productos y stock según los datos originales
        String[] nombres = {"LG TwinWash - 22kg", "Samsung Ecobubble - 20kg", "Mabe AquaSaver - 19kg"};
        int[] stocks = {3, 5, 2}; // Manteniendo la cantidad de stock del código de tu amigo

        boolean stockOk = true;

        if (!productoCarrito.isEmpty()) {
            System.out.println("🛒 Productos en carrito:");

            // Verificar disponibilidad de cada producto en el carrito
            for (int i = 0; i < nombres.length; i++) {
                if (productoCarrito.contains(nombres[i])) {
                    if (stocks[i] <= 0) {
                        System.out.println("❌ Stock insuficiente para: " + nombres[i]);
                        stockOk = false;
                    } else {
                        System.out.println("- " + nombres[i] + " (Disponible)");
                        stocks[i]--; // Reducir el stock
                    }
                }
            }

            if (stockOk) {
                System.out.println("\n✅ Pedido procesado con éxito.");
            } else {
                System.out.println("\n⚠️ Pedido no procesado por falta de stock.");
            }
        } else {
            System.out.println("⚠️ No hay productos en el carrito.");
        }
    }

    // 9. Elegir Tipo de Envío
    static void elegirEnvio() {
        System.out.println("\n--- Tipo de Envío ---");
        System.out.println("1. Envío normal (S/. 20)");
        System.out.println("2. Envío express (S/. 50)");

        System.out.print("Seleccione el tipo de envío: ");
        int opcionEnvio = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        double costoEnvio = 0;
        String tipoEnvio = "";

        switch (opcionEnvio) {
            case 1:
                tipoEnvio = "Normal";
                costoEnvio = 20;
                break;
            case 2:
                tipoEnvio = "Express";
                costoEnvio = 50;
                break;
            default:
                System.out.println("❌ Opción inválida. Se asignará envío normal por defecto.");
                tipoEnvio = "Normal";
                costoEnvio = 20;
        }

        precioCarrito += costoEnvio;
        System.out.println("🚚 Tipo de envío seleccionado: " + tipoEnvio + " | Costo: S/. " + costoEnvio);
    }
// PAUL
    // Método para validar número de tarjeta (16 dígitos)
    private static boolean validarTarjeta(String tarjeta) {
        return tarjeta.matches("\\d{16}");
    }

    // Método para validar CVV (3 dígitos)
    private static boolean validarCVV(String cvv) {
        return cvv.matches("\\d{3}");
    }

    // 10. Seleccionar Método de Pago con validación completa
    static void metodoPago() {
        System.out.println("\n--- Método de Pago ---");
        System.out.println("1. Tarjeta de crédito");
        System.out.println("2. Tarjeta de débito");

        System.out.print("Seleccione su método de pago: ");
        int metodo = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        if (metodo != 1 && metodo != 2) {
            System.out.println("❌ Opción inválida. Debe seleccionar tarjeta de crédito o débito.");
            return;
        }

        String tipoPago = (metodo == 1) ? "Tarjeta de crédito" : "Tarjeta de débito";

        // Solicitar datos de tarjeta y validarlos
        String numeroTarjeta, cvvTarjeta;
        do {
            System.out.print("Ingrese el número de tarjeta (16 dígitos): ");
            numeroTarjeta = sc.nextLine();
            if (!validarTarjeta(numeroTarjeta)) {
                System.out.println("❌ Número de tarjeta inválido. Debe tener 16 dígitos numéricos.");
            }
        } while (!validarTarjeta(numeroTarjeta));

        do {
            System.out.print("Ingrese el código CVV (3 dígitos): ");
            cvvTarjeta = sc.nextLine();
            if (!validarCVV(cvvTarjeta)) {
                System.out.println("❌ CVV inválido. Debe contener exactamente 3 dígitos numéricos.");
            }
        } while (!validarCVV(cvvTarjeta));

        // Solicitar dirección de envío
        System.out.print("Ingrese su departamento: ");
        String departamento = sc.nextLine();

        System.out.print("Ingrese su provincia: ");
        String provincia = sc.nextLine();

        System.out.print("Ingrese su distrito: ");
        String distrito = sc.nextLine();

        System.out.print("Ingrese su dirección exacta: ");
        String direccion = sc.nextLine();

        // Confirmar método de pago y datos ingresados
        System.out.println("\n✅ Método de pago seleccionado: " + tipoPago);
        System.out.println("💳 Tarjeta validada correctamente.");
        System.out.println("📍 Dirección de entrega:");
        System.out.println("Departamento: " + departamento);
        System.out.println("Provincia: " + provincia);
        System.out.println("Distrito: " + distrito);
        System.out.println("Dirección: " + direccion);
    }
    // 11. Procesar Pago
    static void procesarPago() {
        System.out.println("\n--- Procesar Pago ---");

        if (precioCarrito == 0) {
            System.out.println("⚠️ No hay productos en el carrito. Agregue productos antes de pagar.");
            return;
        }

        System.out.println("💰 Total a pagar: S/. " + precioCarrito);
        System.out.print("Ingrese el monto con el que pagará: S/. ");
        double montoIngresado = sc.nextDouble();
        sc.nextLine(); // Limpiar buffer

        if (montoIngresado < precioCarrito) {
            System.out.println("❌ Monto insuficiente. Faltan S/. " + (precioCarrito - montoIngresado));
        } else {
            double cambio = montoIngresado - precioCarrito;
            System.out.println("✅ Pago realizado con éxito.");
            if (cambio > 0) {
                System.out.println("💵 Su cambio es S/. " + cambio);
            }
        }
    }

    // 12. Generar Boleta de Venta
    static void generarComprobante() {
        System.out.println("\n--- Boleta de Venta ---");

        if (precioCarrito == 0 || nombreGuardado == null) {
            System.out.println("⚠️ No se puede generar la boleta. Asegúrese de haber registrado un usuario y agregado productos al carrito.");
            return;
        }

        double subtotal = precioCarrito;
        double igv = subtotal * 0.18;
        double totalPagar = subtotal + igv;

        System.out.println("----BOLETA DE VENTA---");
        System.out.println("Nombre: " + nombreGuardado);
        System.out.println("Apellidos: " + apellidosGuardados);
        System.out.println("DNI: " + dniGuardado);

        System.out.println("\nDetalles de Pago:");
        System.out.println("Subtotal: S/. " + subtotal);
        System.out.println("IGV (18%): S/. " + igv);
        System.out.println("Total a pagar: S/. " + totalPagar);

        System.out.println("\n GRACIAS POR SU COMPRA.");
    }

}