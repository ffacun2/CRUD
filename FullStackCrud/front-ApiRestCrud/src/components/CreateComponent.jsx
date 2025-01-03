import { useEffect, useState } from "react"
import {createPersona,fetchPersonas, updatePersona} from "../services/ClienteService"

export const CreateComponent = ({setClientes, clientes, editingCliente, setEditingCliente}) => {

    const [dni,setDni] = useState('')
    const [nombre,setNombre] = useState('');
    const [apellido,setApellido] = useState('');

    useEffect( () => {
        if(editingCliente){
            setDni(editingCliente.dni);
            setNombre(editingCliente.nombre);
            setApellido(editingCliente.apellido);

        }
    },[editingCliente]);

    const createCliente = async (e) => {
        e.preventDefault();
        const cliente = {dni, nombre, apellido};
        try{
            if(editingCliente){
                cliente.id = editingCliente.id;
                await updatePersona(cliente);
            }
            else{
                await createPersona(cliente);
            }
            const response = await fetchPersonas();
            setClientes(response);
            clearForm();
        }catch(error){
            console.error('Error al crear/actualizar la persona', error)
            alert("error al crear/actualizar la persona.");
        }
    }

    const clearForm = () => {
        setDni("");
		setNombre("");
		setApellido("");
		setEditingCliente(null);
    }


  return (
    <>
    <div className="d-flex justify-content-center my-3">
        <div className="card col">
            <div className="card-body">
                <h4>Crear Cliente</h4>
                <form onSubmit={createCliente}>

                    <div className="row my-3">
                        <div className="col">
                            <label htmlFor="inputName">Nombre</label>
                            <input type="text" className="form-control" id="inputName" placeholder="Ingrese el Nombre" value = {nombre} onChange={e => setNombre(e.target.value)}/>
                        </div>
                        <div className="col">
                            <label htmlFor="inputLastName">Apellido</label>
                            <input type="text" className="form-control" id="inputLastName" placeholder="Ingrese el Apellido" value = {apellido} onChange={e => setApellido(e.target.value)}/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="col-6">
                            <label htmlFor="inputDni">DNI</label>
                            <input type="number" className="form-control" id="inputDni" placeholder="Ingrese DNI" value = {dni} onChange={e => setDni(e.target.value)}/>
                        </div>
                    </div>
                    <div className="d-flex justify-content-center">
                        <button type="submit" className="btn btn-primary my-3">{editingCliente?'Editar':'Crear'}</button>
                        <button type="button" className="btn btn-secondary m-3" onClick={clearForm}>Limpiar</button>
                    </div>

                </form>
                
            </div>
        </div>
    </div>
    </>
  )
}
