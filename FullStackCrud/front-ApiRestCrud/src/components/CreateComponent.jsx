import { useState } from "react"
import {createPersona,fetchPersonas} from "../services/ClienteService"

export const CreateComponent = ({setClientes, clientes}) => {

    const [dni,setDni] = useState('')
    const [nombre,setNombre] = useState('');
    const [apellido,setApellido] = useState('');

    const createCliente = async (e) => {
        e.preventDefault();
        const cliente = {dni, nombre, apellido};
        try{
            await createPersona(cliente);
            const response = await fetchPersonas();
            setClientes(response);
            setDni("");
			setNombre("");
			setApellido("");
        }catch(error){
            console.error('Error al crear la persona', error)
            alert("error al crear la persona.");
        }
    }

  return (
    <>
    <div className="d-flex justify-content-center my-3">
        <div className="card col-4">
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
                        <button type="submit" className="btn btn-primary my-3">Crear</button>
                    </div>

                </form>
                
            </div>
        </div>
    </div>
    </>
  )
}
