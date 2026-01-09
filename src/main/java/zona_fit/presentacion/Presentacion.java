package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class Presentacion {
    public static void main(String[] args) {
        System.out.println("*** Panel de Administracion de Clientes Gimnasio ZonaFit *** ");
        boolean salir = false;
        Scanner sc = new Scanner(System.in);
        IClienteDAO clienteDAO = new ClienteDAO();
        while(!salir){
            System.out.print("""
                    1. Lista de Clientes
                    2. Agregar un Cliente
                    3. Modificar un Cliente
                    4. Eliminar un Cliente
                    5. Salir
                    Ingrese una opcion:\s""");

            var eleccionUsuario = Integer.parseInt(sc.nextLine());
            switch (eleccionUsuario){
                case 1: {
                    System.out.println("*** Listar Clientes ***");
                    var clientes = clienteDAO.listarClientes();
                    clientes.forEach(System.out::println);
                    break;
                }
                case 2: {
                    Cliente nuevoCliente = new Cliente();

                    System.out.print("Ingrese el nombre del Cliente: ");
                    String nombreClienteNuevo = sc.nextLine();
                    nuevoCliente.setNombre(nombreClienteNuevo);

                    System.out.print("Ingrese el Apellido del Cliente: ");
                    String apellidoClienteNuevo = sc.nextLine();
                    nuevoCliente.setApellido(apellidoClienteNuevo);

                    System.out.print("Ingrese el valor de la membresia del cliente: ");
                    int valorMembresiaClienteNuevo = Integer.parseInt(sc.nextLine());
                    nuevoCliente.setMembresia(valorMembresiaClienteNuevo);

                    var agregado = clienteDAO.agregarCliente(nuevoCliente);
                    if(agregado)
                      System.out.println("Cliente agregado: " + nuevoCliente);
                    else
                        System.out.println("No se agrego el cliente: " + nuevoCliente);
                    break;

                }
                case 3: {
                    Cliente clienteActualizado = new Cliente();
                    System.out.print("Ingrese el ID del cliente que quiera modificar: ");
                    int idClienteAModificar = Integer.parseInt(sc.nextLine());
                    clienteActualizado.setId(idClienteAModificar);

                    System.out.print("Ingrese el nuevo Nombre del cliente: ");
                    var nombreClienteModificado = sc.nextLine();
                    clienteActualizado.setNombre(nombreClienteModificado);

                    System.out.print("Ingrese el nuevo Apellido del cliente: ");
                    var apellidoClienteModificado = sc.nextLine();
                    clienteActualizado.setApellido(apellidoClienteModificado);

                    System.out.print("Ingrese el nuevo valor de la membresia del cliente: ");
                    var valorMembresiaModificada = Integer.parseInt(sc.nextLine());
                    clienteActualizado.setMembresia(valorMembresiaModificada);

                    boolean modificado = clienteDAO.modificarCliente(clienteActualizado);
                    if(modificado)
                      System.out.println("Cliente modificado: " + clienteActualizado);
                     else{
                        System.out.println("Error al modificar cliente");
                    }
                     break;
                }
                case 4: {
                    Cliente clienteABorrar = new Cliente();
                    System.out.print("Ingrese el id del cliente que desea borrar: ");
                    int idClienteABorrar = Integer.parseInt(sc.nextLine());

                    var borrado = clienteDAO.eliminarCliente(clienteABorrar);
                    if(borrado)
                        System.out.println("Cliente eliminado: " + clienteABorrar);
                    else
                        System.out.println("No se elimino el cliente: " + clienteABorrar);

                }
                case 5: {
                    System.out.println("Saliendo del panel de Administracion de Clientes Gimnasio ZonaFit... ");
                    salir = true;
                    System.out.println("Vuelve pronto :)");
                    break;
                }
                default:
                    System.out.println("Opcion no valida");

            }

            }
        }

}

