import carouselImage1 from "../images/1.jpg";
import c2 from "../images/2.jpg";
import classes from "./Carousel.module.css";

const Carousel = () => {
    return (
        <div
            id="HomeCarousel"
            className="carousel slide carousel-fade"
            data-bs-ride="true"
        >
            <div className="carousel-inner">
                <div className={`carousel-item active ${classes.cItem}`}>
                    <img
                        src={carouselImage1}
                        className={`d-block w-100 ${classes.cImg}`}
                        alt="..."
                    />
                    <div
                        className={`carousel-caption d-none d-md-block ${classes.captionTitle}`}
                    >
                        <h3>Learn to play</h3>
                    </div>
                </div>
                <div className={`carousel-item ${classes.cItem}`}>
                    <img
                        src={c2}
                        className={`d-block w-100 ${classes.cImg}`}
                        alt="..."
                    />
                    <div
                        className={`carousel-caption d-none d-md-block ${classes.captionTitle}`}
                    >
                        <h3>Teach to play</h3>
                    </div>
                </div>
            </div>
            <button
                className="carousel-control-prev"
                style={{filter: "invert(100%)"}}
                type="button"
                data-bs-target="#HomeCarousel"
                data-bs-slide="prev"
            >
            <span
                className="carousel-control-prev-icon"
                aria-hidden="true"
            ></span>
                <span className="visually-hidden">Previous</span>
            </button>
            <button
                className="carousel-control-next" style={{filter: "invert(100%)"}}
                type="button"
                data-bs-target="#HomeCarousel"
                data-bs-slide="next"
            >
            <span
                className="carousel-control-next-icon"
                aria-hidden="true"
            ></span>
                <span className="visually-hidden">Next</span>
            </button>
        </div>

    );
};
export default Carousel;
