const TestimonialSliderItem = ({review, demoImage, index}) => {
    return (<div key={review.id} className={`carousel-item ${index === 0 ? 'active' : null}`}>
        <img className="rounded-circle shadow-1-strong mb-4 mt-2"
             src={demoImage} alt="avatar"
             style={{width: "150px"}}/>
        <div className="row justify-content-center">
            <div className="col-8">
                <h5>{review.owner.username}</h5>
                <p className="text-muted">
                    <i className="fas fa-quote-left pe-2"/>
                    {review.reviewText}
                </p>
            </div>
        </div>
    </div>)
}
export default TestimonialSliderItem