import {useState} from "react";
import {CardElement, useElements, useStripe} from "@stripe/react-stripe-js";
import {useAtom} from "jotai";
import state from "../state";
import {PostDataAuthenticated} from "../services/FetchDataService";
import {toast} from "react-toastify";

const PaymentForm = ({courseId, addCourse}) => {
    const [UserToken] = useAtom(state.token)
    const [processing, setProcessing] = useState(false);
    const [error, setError] = useState(null);
    const stripe = useStripe();
    const elements = useElements();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setProcessing(true);

        const {error, token} = await stripe.createToken(elements.getElement(CardElement));

        if (error) {
            setError(error);
            setProcessing(false);
        } else {
            console.log(token)
            const payload = JSON.stringify({token: token.id, courseId: courseId})
            const response = await PostDataAuthenticated(payload, UserToken, "payment/charge")
            console.log(response)

            if (response.message) {
                toast.info("Successful payment")
                await addCourse();
                setProcessing(false);
            } else {
                setError('Error processing payment');
                setProcessing(false);
            }
        }

    }

    const cardStyle = {
        style: {
            base: {
                fontFamily: 'Arial, sans-serif',
                fontSmoothing: "antialiased",
                fontSize: "20px",
                "::placeholder": {
                    color: "#32325d"
                }
            },
            invalid: {
                fontFamily: 'Arial, sans-serif',
                color: "#fa755a",
                iconColor: "#fa755a"
            }
        }
    }
    return (
        <form onSubmit={handleSubmit}>
            <CardElement id="card-element" options={cardStyle}/>
            {error && <div className="errors">{error.message}</div>}
            <div className="d-flex justify-content-end">
                <button type="submit" className="btn my-4 " disabled={processing}>Pay</button>
            </div>
        </form>
    );
}
export default PaymentForm