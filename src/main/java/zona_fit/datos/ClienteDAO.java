package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import static zona_fit.conexion.Conexion.getConnection;

public class ClienteDAO implements IClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = getConnection();
        var sql = "SELECT * FROM cliente ORDER BY id";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                var cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al listar clientes\n" + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión\n" + e.getMessage());
            }
        }

        return clientes;
    }

    @Override
    public boolean buscarClientePorId(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conexion = getConnection();
        String sql = "SELECT * FROM cliente where id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error al buscar cliente por id: " + cliente.getId() + "\n" + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión\n" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = getConnection();
        String sql = "INSERT INTO cliente(nombre, apellido, membresia) " + "VALUES(?, ?, ?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.execute();
            System.out.println("Cliente agregado correctamente\n");
            return true;

        } catch (Exception e) {
            System.out.println("Error al agrergar cliente\n" + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión\n" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = getConnection();
        String sql = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? " + " WHERE id = ?";

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());

            ps.execute();
            System.out.println("Se modificó el cliente con éxito");
            return true;

        } catch (Exception e) {
            System.out.println("Error al modificar cliente\n" + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión\n" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection conexion = getConnection();
        String sql = "DELETE FROM cliente WHERE id = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, cliente.getId());
            ps.execute();
            System.out.println("Se eliminó al cliente correctamente");
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el cliente\n" + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexión\n" + e.getMessage());
            }
        }
        return false;
    }
}

    /*
    public static void main(String[] args) {


        //Buscar por id
        /*
        IClienteDAO clienteDao = new ClienteDAO();
        System.out.println("Buscar por id");
        var cliente1 = new Cliente(2);
        System.out.println("Cliente antes de la busqueda: " + cliente1);
        var encontrado = clienteDao.buscarClientePorId(cliente1);
        if(encontrado){
            System.out.println("Cliente encontrado: " + cliente1);
        }
        else{
            System.out.println("No se encontró registro:" + cliente1);
        }
         */
/*
        //Agregar cliente
        var nuevoCliente = new Cliente("Alejandro", "Garduño", 150);
        IClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.agregarCliente(nuevoCliente);

         //Listar clientes
        System.out.println("Listar clientes");
        var clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
*/

        /*
        //Modificar cliente
        IClienteDAO clienteDAO = new ClienteDAO();
        var modificarCliente = new Cliente(4,"Hector", "Rangel", 500);
        clienteDAO.modificarCliente(modificarCliente);

        //Listar clientes
        System.out.println("Listar clientes");
        var clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);

         */

        /*
        //Listar clientes
        IClienteDAO clienteDAO = new ClienteDAO();
        System.out.println("Listar clientes");
        var clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);

        //Eliminar cliente
        Cliente eliminarCliente = new Cliente(5);
        clienteDAO.eliminarCliente(eliminarCliente);

        //Listar clientes
        System.out.println("Listar clientes");
        clientes = clienteDAO.listarClientes();
        clientes.forEach(System.out::println);
    }
}
    */
