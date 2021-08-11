import { http } from './config'

export default {

    listTasks:(page, amt) => {
        return http.get('tasks?page='+page+'&amt='+amt)
    },

    deleteTask:(id) => {
        return http.delete('tasks/'+id)
    },

    createTask:(task) => {
        return http.post('tasks', task)
    }


}