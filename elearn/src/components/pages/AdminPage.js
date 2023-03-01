import {useAtom} from "jotai";
import state from "../../state";
import {useEffect, useState} from "react";
import {GetDataAuthenticated} from "../../services/FetchDataService";

const AdminPage = () => {
    const [userData] = useAtom(state.userData)
    const [token] = useAtom(state.token);
    const [users, setUsers] = useState([]);
    const [searchTerm, setSearchTerm] = useState("");
    const [selectedUser, setSelectedUser] = useState(null);


    const handleMakeMentor = async (username) => {
        const response = await GetDataAuthenticated(`admin/makeMentor/${username}`, token)
        if (response.message) {
            window.location.reload();
            console.log(response.message);
        }
    }

    const handleChange = (event) => {
        setSearchTerm(event.target.value);
    }


    useEffect(() => {
        (async () => {
            if (token != null) {
                const response = await GetDataAuthenticated("admin/allUsers", token)
                setUsers(response)
            }
        })()
    }, [token])

    const filteredUsers = users.filter((user) => {
        return user.username.includes(searchTerm)
    })

    return (
        <>
            {userData && userData.rol.includes("ADMIN") ? (
                <div>
                    <div className="col">
                        <input
                            className="form-control inputStyle"
                            style={{width: "auto"}}
                            type="text"
                            name="searchBar"
                            id="search"
                            placeholder="Search for users"
                            value={searchTerm}
                            onChange={handleChange}
                        />
                    </div>
                    <div>
                        <ul style={{border: "solid 2px black"}}>
                            {filteredUsers.map((user, index) => (
                                <li key={index}>
                                    <button type="button" onClick={() => setSelectedUser(user)}>{user.username}</button>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div>{selectedUser && (
                        <div style={{border: "solid 2px black"}}>
                            <h2>Username: <span style={{color: "blue"}}>{selectedUser.username}</span></h2>
                            <h2>EmailAdress: <span style={{color: "blue"}}>{selectedUser.emailAddress}</span></h2>
                            <h2>First Name: <span style={{color: "blue"}}>{selectedUser.firstName}</span></h2>
                            <h2>Last Name: <span style={{color: "blue"}}>{selectedUser.lastName}</span></h2>
                            <h2>Roles:
                                <ul>
                                    {selectedUser.roles.map((role, index) => (
                                        <li key={index}><span style={{color: "blue"}}>{role}</span></li>))}
                                </ul>
                            </h2>
                            {!selectedUser.roles.includes("mentor") && <button type="button"
                                                                               onClick={async () => await handleMakeMentor(selectedUser.username)}>Make
                                Mentor</button>}
                        </div>
                    )}</div>
                </div>
            ) : <h1>404 NOT FOUND </h1>}
        </>)
}
export default AdminPage