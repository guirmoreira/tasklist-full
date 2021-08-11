import { http } from './config'

export default {

    listTasks:(page, amt, order) => {
        return http.get('tasks?page='+page+'&amt='+amt+'&oder='+order)
    },

    deleteTask:(id) => {
        return http.delete('tasks/'+id)
    },

    createTask:(task) => {
        return http.post('tasks', task)
    }


}