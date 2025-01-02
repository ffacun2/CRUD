import axios from 'axios';
const CLIENTE_BASE_REST_API_URL = "http://localhost:8080/";


const api = axios.create({
    baseURL: CLIENTE_BASE_REST_API_URL,
    withCredentials: true
});

export const fetchPersonas = async() => {
    try{
        const response = await api.get('/personas');
        return response.data;
    }catch(err){
        console.error('Error fetching personas', err);
        throw err;
    }
}

export const createPersona = async(cliente) => {
    try{
        const response = await api.post('/personas', cliente);
        return response.data;
    }catch(err){
        console.error('Error creating persona', err);
        throw err;
    }
}

export const deletePersona = async(id) => {
    try{
        const response = await api.delete(`/personas/${id}`);
        return response.data;
    }catch(err){
        console.error('Error deleting persona', err);
        throw err;
    }
}

export const updatePersona = async(id, cliente) => {
    try{
        const response = await api.put(`/personas/${id}`, cliente);
        return response.data;
    }catch(err){
        console.error('Error updating persona', err);
        throw err;
    }
}
