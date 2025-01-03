import { useEffect, useState } from "react";
import { CreateComponent } from "./CreateComponent";
import { TablaComponent } from "./TablaComponent";
import { fetchPersonas } from "../services/ClienteService";



export const BodyComponent = () => {
	const [clientes, setClientes] = useState([]);
	const [editingCliente, setEditingCliente] = useState(null);

    useEffect(() => {
		const getPersonas = async () => {
			try {
				const response = await fetchPersonas();
				setClientes(response);
			} catch (err) {
				console.error("Error fetching personas", err);
			}
		};
		getPersonas();
	}, []);

	return (
        <>
		<div className="container-fluid col-lg-6 col-md-7 col-sm-12">
            <CreateComponent setClientes={setClientes} clientes={clientes} editingCliente={editingCliente} setEditingCliente={setEditingCliente}/>
            <TablaComponent setClientes={setClientes} clientes={clientes} setEditingCliente={setEditingCliente}/>
		</div>
        </>
    );
};
