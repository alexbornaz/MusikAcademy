const InfoModal = ({title, message, showModal, setShowModal}) => {
    const handleCloseModal = () => {
        setShowModal(false);
    }
    return (
        <div className={`modal fade ${showModal ? 'show' : ''}`} id="infoModal" data-bs-backdrop="static"
             data-bs-keyboard="false"
             tabIndex="-1" style={{display: showModal ? 'block' : 'none'}}
             aria-labelledby="infoModalLabel" aria-hidden={!showModal}>
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                    <div className="modal-header">
                        <h1 className="modal-title fs-5" id="infoModalLabel">{title}</h1>
                        <button type="button" className="btn-close" onClick={handleCloseModal}
                                aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <h3>{message}</h3>
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn" onClick={handleCloseModal}>Close</button>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default InfoModal;