import {Component} from 'react';
import './App.css';
import router from './router/router'
import {RSA} from "./pages/Login";
import Axios from 'axios';
import {Feedback} from "@icedesign/base";
import {Route} from 'react-router-dom';

const {toast} = Feedback;


class App extends Component {
    componentWillMount() {
        let self = this;
        if (window.sessionStorage.getItem('Authorization')) {
            Axios.defaults.headers.common['Authorization'] = "Bearer " + window.sessionStorage.getItem('Authorization');
        }
        if (window.sessionStorage.getItem('x-login-token')) {
            Axios.defaults.headers.common['x-login-token'] = window.sessionStorage.getItem('x-login-token');
        }

        Axios.interceptors.response.use((response) => {
            return Promise.resolve(response);
        }, (error) => {
            if (error.response) {
                switch (error.response.status) {
                    case 403: {
                        window.sessionStorage.clear();
                        toast.show({
                            type: "error",
                            content: "Token失效, 请重新登陆",
                            duration: 5000
                        });
                        window.location.replace("#/login");
                        window.location.reload();
                        break;
                    }
                    case 500: {
                        if (error.response.data.message === 'pre:AccessTokenZuulFilter') {
                            window.sessionStorage.clear();
                            RSA()
                            toast.show({
                                type: "error",
                                content: "Token失效, 请重新登陆",
                                duration: 5000
                            });
                            window.location.replace("#/login");
                            window.location.reload();
                        }
                        break;
                    }
                    default : {
                        toast.show({
                            type: "error",
                            content: error.response.data.message,
                            duration: 1000
                        });
                    }
                }
            }
            return Promise.reject(error);
        });
        setInterval(() => {
            RSA()
        }, 3540000)
    }

    render() {
        return router;
    }
}

export default App;
