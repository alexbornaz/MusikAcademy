import { Navigate } from "react-router-dom";

export const PrivateRoute = ({ children}) => {
    const isAuthenticated = true;
        
    if (isAuthenticated ) {
      return children
    }
      
    return <Navigate to="/" />
  }