import TestimonialSliderItem from "./TestimonialSliderItem";

const TestimonialSlider = ({reviews}) => {
    const demoImage = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png"
    return (
        <div id="carouselExample" className="carousel slide my-4 testimonialSlider" >
            <h4 className="text-center mt-2">Reviews</h4>
            <div className="carousel-inner text-center">
                {reviews && reviews.map((review,index) => (
                    <TestimonialSliderItem key={index} demoImage={demoImage} review={review} index={index}/>))
                }
                <button className="carousel-control-prev" type="button" data-bs-target="#carouselExample"
                        data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true" style={{filter:"invert(100%)"}}></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#carouselExample"
                        data-bs-slide="next">
                    <span className="carousel-control-next-icon" style={{filter:"invert(100%)"}} aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    )
}
export default TestimonialSlider