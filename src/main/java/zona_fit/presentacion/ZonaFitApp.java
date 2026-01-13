package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {

        zonaFitApp();

    }
    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        var clienteDAO = new ClienteDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola,opcion,clienteDAO);

            }catch (Exception e ){
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }
    private static int mostrarMenu(Scanner consola){

            System.out.print("""
                    *** Panel de Administracion de Clientes Gimnasio ZonaFit ***
                    1. Lista de Clientes
                    2. Buscar Clientes
                    3. Agregar un Cliente
                    4. Modificar un Cliente
                    5. Eliminar un Cliente
                    6. Salir
                    Ingrese una opcion:\s""");
            return Integer.parseInt(consola.nextLine());

    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion,
                                            IClienteDAO clienteDAO){
            var salir = false;
            switch(opcion){
                case 1 -> { // 1. Listar Clientes
                    System.out.println("--- Listado de Clientes ---");
                    var clientes = clienteDAO.listarClientes();
                    clientes.forEach(System.out::println);
                }

                case 2 ->{ // 2. Buscar cliente por ID
                    System.out.println("Introduce el id del Cliente a buscar: ");
                    var idCliente =  Integer.parseInt(consola.nextLine());
                    var cliente = new Cliente(idCliente);
                    var encontardo = clienteDAO.buscarClientePorId(cliente);
                    if(encontardo){
                        System.out.println("Cliente encontrado: " + cliente);
                    }
                    else
                        System.out.println("Cliente NO encontrado: " + cliente);


                }

                case 3 -> {
                    Cliente nuevoCliente = new Cliente();

                    System.out.print("Ingrese el nombre del Cliente: ");
                    String nombreClienteNuevo = consola.nextLine();
                    nuevoCliente.setNombre(nombreClienteNuevo);

                    System.out.print("Ingrese el Apellido del Cliente: ");
                    String apellidoClienteNuevo = consola.nextLine();
                    nuevoCliente.setApellido(apellidoClienteNuevo);

                    System.out.print("Ingrese el valor de la membresia del cliente: ");
                    int valorMembresiaClienteNuevo = Integer.parseInt(consola.nextLine());
                    nuevoCliente.setMembresia(valorMembresiaClienteNuevo);

                    var agregado = clienteDAO.agregarCliente(nuevoCliente);
                    if(agregado)
                        System.out.println("Cliente agregado: " + nuevoCliente);
                    else
                        System.out.println("No se agrego el cliente: " + nuevoCliente);
                }

                case 4 -> {
                    Cliente clienteActualizado = new Cliente();
                    System.out.print("Ingrese el ID del cliente que quiera modificar: ");
                    int idClienteAModificar = Integer.parseInt(consola.nextLine());
                    clienteActualizado.setId(idClienteAModificar);

                    System.out.print("Ingrese el nuevo Nombre del cliente: ");
                    var nombreClienteModificado = consola.nextLine();
                    clienteActualizado.setNombre(nombreClienteModificado);

                    System.out.print("Ingrese el nuevo Apellido del cliente: ");
                    var apellidoClienteModificado = consola.nextLine();
                    clienteActualizado.setApellido(apellidoClienteModificado);

                    System.out.print("Ingrese el nuevo valor de la membresia del cliente: ");
                    var valorMembresiaModificada = Integer.parseInt(consola.nextLine());
                    clienteActualizado.setMembresia(valorMembresiaModificada);

                    boolean modificado = clienteDAO.modificarCliente(clienteActualizado);
                    if(modificado)
                        System.out.println("Cliente modificado: " + clienteActualizado);
                    else{
                        System.out.println("Error al modificar cliente");
                    }

                }
                case 5 -> {
                    Cliente clienteABorrar = new Cliente();
                    System.out.print("Ingrese el id del cliente que desea borrar: ");
                    int idClienteABorrar = Integer.parseInt(consola.nextLine());
                    clienteABorrar.setId(idClienteABorrar);
                    var borrado = clienteDAO.eliminarCliente(clienteABorrar);
                    if(borrado)
                        System.out.println("Cliente eliminado: " + clienteABorrar);
                    else
                        System.out.println("No se elimino el cliente: " + clienteABorrar);

                }

                case 6 -> {
                    System.out.println("Saliendo del panel de Administracion de Clientes Gimnasio ZonaFit... ");
                    salir = true;
                    System.out.println("Vuelve pronto :)");
                }
                default -> System.out.println("Opcion no valida");
            }



        return salir;
    }
}

