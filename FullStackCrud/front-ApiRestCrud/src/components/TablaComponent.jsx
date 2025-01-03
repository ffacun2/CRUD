import {deletePersona} from '../services/ClienteService';

export const TablaComponent = ({setClientes, clientes, setEditingCliente }) => {

    const deleteCliente = async (id) => {
        try{
             await deletePersona(id);
             setClientes(clientes.filter(cliente => cliente.id !== id));
        }catch(error){
            console.error('Error al borrar la persona', error)
            alert("error al borrar la persona.");
        }
    }

    const startEditing = (cliente) => {
        setEditingCliente(cliente);
    }


    const generateTable = () => {
        return clientes.map((cliente) => {
            return (
                <tr key={cliente.id}>
                    <td>{cliente.dni}</td>
                    <td>{cliente.nombre}</td>
                    <td>{cliente.apellido}</td>
                    <td>
                        <div className="d-flex justify-content-around">
                            <button className="btn btn-warning" onClick={() => startEditing(cliente)}>Editar</button>
                            <button className="btn btn-danger" onClick={() => deleteCliente(cliente.id)}>Borrar</button>
                        </div>
                    </td>
                </tr>
            )
        })
    }

  return (
    <>
        <div className="d-flex justify-content-center">
            <div className="card col">
                <div className="card-body">
                    <table className="table text-center">
                        <thead className="table-light">
                            <tr>
                                <th>DNI</th>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {generateTable()}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </>
  )
}
