import { useEffect, useState } from "react";
import { CreateComponent } from "./CreateComponent";
import { TablaComponent } from "./TablaComponent";
import { fetchPersonas } from "../services/ClienteService";



export const BodyComponent = () => {
	const [clientes, setClientes] = useState([]);

    useEffect(() => {
		const getPersonas = async () => {
			try {
				const response = await fetchPersonas();
                console.log(response);
				setClientes(response);
			} catch (err) {
				console.error("Error fetching personas", err);
			}
		};
		getPersonas();
	}, []);

	return (
        <>
            <CreateComponent setClientes={setClientes} clientes={clientes}/>
            <TablaComponent setClientes={setClientes} clientes={clientes} />
        </>
    );
};
