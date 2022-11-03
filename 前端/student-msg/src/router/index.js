import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/views/Layout/index'
import Login from '@/views/Login/index'
import Home from '@/views/Home/index'
import StudentAdd from '@/views/StudentAdd/index'
import StudentQuery from '@/views/StudentQuery/index'
import StudentUpdate from '@/views/StudentUpdate/index'
import TeacherAdd from '@/views/TeacherAdd/index'
import TeacherQuery from '@/views/TeacherQuery/index'
import TeacherUpdate from '@/views/TeacherUpdate/index'
import UserInfo from '@/views/UserInfo/index'
import PersonalInfo from "@/views/UserInfo/component/PersonalInfo";
import PersonalQuotes from "@/views/UserInfo/component/PersonalQuotes";
import PersonalUpdate from "@/views/UserInfo/component/PersonalUpdate";
import PersonalSetting from "@/views/UserInfo/component/PersonalSetting";
import Announcement from "@/views/Announcement/index"
import ManagementAnnouncement from '@/views/ManagementAnnounement/index'

Vue.use(VueRouter)

const routes = [
    {
        path:'/',
        component:Layout,
        redirect: '/home',
        children:[
            {
                path:'home',
                name:'Home',
                component:Home
            },
            {
                path: 'studentAdd',
                name:'StudentAdd',
                component: StudentAdd
            },
            {
                path: 'studentQuery',
                name:'StudentQuery',
                component: StudentQuery
            },
            {
                path: 'studentUpdate',
                name:'StudentUpdate',
                component: StudentUpdate
            },
            {
                path: 'teacherAdd',
                name:'TeacherAdd',
                component: TeacherAdd
            },
            {
                path: 'teacherQuery',
                name:'TeacherQuery',
                component: TeacherQuery
            },
            {
                path: 'teacherUpdate',
                name:'TeacherUpdate',
                component: TeacherUpdate
            },
            {
              path: 'announcement',
              name:'Announcement',
              component:Announcement
            },
            {
                path: 'managementAnnouncement',
                name: 'ManagementAnnouncement',
                component: ManagementAnnouncement
            },
            {
                path: 'userInfo',
                name:'UserInfo',
                component: UserInfo,
                redirect:'/userInfo/personalInfo',
                children:[
                    {
                        path:'personalInfo',
                        name:'PersonalInfo',
                        component:PersonalInfo
                    },
                    {
                        path:'personalQuotes',
                        name:'PersonalQuotes',
                        component:PersonalQuotes
                    },
                    {
                        path:'personalUpdate',
                        name:'PersonalUpdate',
                        component:PersonalUpdate
                    },
                    {
                        path:'personalSetting',
                        name:'PersonalSetting',
                        component:PersonalSetting
                    }
                ]
            }
        ]
    },
    {
        path: '/login',
        name:'Login',
        component: Login,
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
