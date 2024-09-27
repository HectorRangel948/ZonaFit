package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestZonaFit {
    public static void main(String[] args) {
        zonaFit();
    }

    private static void zonaFit(){
        int seleccion;
        boolean salir = false;
        Scanner console = new Scanner(System.in);
        Cliente cliente = new Cliente();
        IClienteDAO clienteDAO = new ClienteDAO();

        System.out.print("Bienvenido a ZonaFit");
        while(!salir){
            System.out.println("""
                \n
                1.-Listar clientes
                2.-Buscar cliente
                3.-Crear nuevo cliente
                4.-Modificar cliente existente
                5.-Eliminar cliente
                6.-Salir
                """);
            seleccion = console.nextInt();
            console.nextLine();
            switch(seleccion){
                case 1 -> listarClientes(clienteDAO);
                case 2 -> buscarCliente(cliente, console, clienteDAO);
                case 3 -> crearCliente(cliente, console, clienteDAO);
                case 4 -> modificarCliente(clienteDAO, console);
                case 5 -> eliminarCliente(cliente, console, clienteDAO);
                case 6 -> salir=salirZonaFit();
                default -> System.out.println("Seleccione una opción válida");
            }
        }
    }

    private static void listarClientes(IClienteDAO clienteDAO){
        List<Cliente> clientes;
        clientes=clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }

    private static void buscarCliente(Cliente cliente, Scanner console, IClienteDAO clienteDAO){
        try{
        System.out.print("Ingrese el id del cliente que desea buscar: ");
        cliente = new Cliente(console.nextInt());
        var encontrado=clienteDAO.buscarClientePorId(cliente);
        if(encontrado){
            System.out.println(cliente);
        }else{
            System.out.println("No se encontró al cliente: " + cliente);
        }
        }catch(Exception e){
            System.out.println("Ocurrió un error al buscar al cliente\n" + e.getMessage());
        }
    }

    private static void crearCliente(Cliente cliente, Scanner console, IClienteDAO clienteDAO){
        try{
            System.out.print("Nombre:");
            cliente.setNombre(console.nextLine());
            System.out.print("Apellido:");
            cliente.setApellido(console.nextLine());
            System.out.print("Membresía:");
            cliente.setMembresia(console.nextInt());
        }catch(Exception e){
            System.out.println("Error al agregar nuevo cliente\n" + e.getMessage());
        }

        try{
            clienteDAO.agregarCliente(cliente);
        }catch(Exception e){
            System.out.println("Error al crear cliente\n" + e.getMessage());
        }
    }

    private static void modificarCliente(IClienteDAO clienteDAO, Scanner console){
        listarClientes(clienteDAO);
        Cliente cliente = new Cliente();
        try{
        System.out.print("\nIngrese el id del cliente a modificar: ");
        cliente.setId(console.nextInt());
        console.nextLine();
        System.out.print("Igrese el nuevo nombre: ");
        cliente.setNombre(console.nextLine());
        System.out.print("Ingrese el nuevo apellido: ");
        cliente.setApellido(console.nextLine());
        System.out.print("Ingrese la nueva membresía: ");
        cliente.setMembresia(console.nextInt());

        clienteDAO.modificarCliente(cliente);

        listarClientes(clienteDAO);

        }catch(Exception e){
            System.out.println("Error al modificar la información del cliente\n" + e.getMessage());
        }
    }

    private static void eliminarCliente(Cliente cliente, Scanner console,IClienteDAO clienteDAO){
        listarClientes(clienteDAO);
        try{
            System.out.print("\nIngrese el id del ciente que desa eliminar: ");
            cliente.setId(console.nextInt());
            clienteDAO.eliminarCliente(cliente);
            listarClientes(clienteDAO);
        }catch(Exception e){
            System.out.println("Ocurrió un error al intentar eliminar al cliente\n"+ e.getMessage());
        }
    }

    private static boolean salirZonaFit(){
        System.out.println("Hasta pronto");
        return true;
    }
}
