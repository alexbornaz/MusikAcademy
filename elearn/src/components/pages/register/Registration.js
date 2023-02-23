import AuthLayout from "../../authenticationComponents/AuthLayout";
import RegistrationForm from "./RegistrationForm";

const Registration = () => {
    return (
        <AuthLayout children={<RegistrationForm/>}/>
    )
};
export default Registration;
