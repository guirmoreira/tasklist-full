import { http } from './config'

export default {

    listTasks:(page, amt, sort, dir) => {
        return http.get('tasks?page='+page+'&amt='+amt+'&sort='+sort+'&dir='+dir)
    },

    deleteTask:(id) => {
        return http.delete('tasks/'+id)
    },

    createTask:(task) => {
        return http.post('tasks', task)
    }


}