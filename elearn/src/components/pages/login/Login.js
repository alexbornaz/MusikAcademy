import AuthLayout from "../../authenticationComponents/AuthLayout";
import LoginForm from "./LoginForm";
const Login = () => {
  return (
    <AuthLayout children={<LoginForm />}/>
  );
};
export default Login;
