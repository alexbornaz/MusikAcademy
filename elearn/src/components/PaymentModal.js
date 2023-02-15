import PaymentForm from "./PaymentForm";
import {Elements} from "@stripe/react-stripe-js";
import {loadStripe} from "@stripe/stripe-js";


const PaymentModal = ({showModal, setShowModal, courseId, addCourse}) => {
    const stripePromise = loadStripe("pk_test_51MbjubH83x7MGW43sf9teaQJKVoiVcYbTWyvbZYmr0uEFoFH71FoqKjyc1z8WxBbTrF83B1kWHcb4l0b4IEEqxhA00VejkIjLf")
    return (
        <div className={`modal fade${showModal ? ' show' : ''}`} tabIndex="-1" data-bs-backdrop="static"
             style={{display: showModal ? 'block' : 'none'}} aria-hidden={showModal ? null : 'true'}
             aria-modal={showModal ? 'true' : null}>
            <div className="modal-dialog modal-dialog-centered" role="document" aria-hidden={showModal}>
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Payment</h5>
                        <button type="button" className="btn-close" onClick={() => setShowModal(false)}>
                            <span aria-hidden="true"></span>
                        </button>
                    </div>
                    <Elements
                        stripe={stripePromise}>
                        <PaymentForm courseId={courseId} addCourse={addCourse}/>
                    </Elements>
                </div>
            </div>
        </div>
    )
}
export default PaymentModal