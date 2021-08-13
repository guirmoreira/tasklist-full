import { http } from './config'

export default {

    listTasks:(page, amt, sort, dir, status) => {
        return http.get('tasks?page='+page+'&amt='+amt+'&sort='+sort+'&dir='+dir+'&status='+status)
    },

    deleteTask:(id) => {
        return http.delete('tasks/'+id)
    },

    createTask:(task) => {
        return http.post('tasks', task)
    },

    updateTask:(id, task) => {
        return http.put('tasks/'+id, task)
    }


}