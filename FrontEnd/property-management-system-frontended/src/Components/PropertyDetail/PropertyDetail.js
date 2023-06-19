import axios from "axios";
import { useEffect, useState, Fragment, useCallback, useMemo, useRef } from "react";
import { useNavigate, useParams } from "react-router";



const PropertyDetail = (props) => {

    const params = useParams();
    const navigate = useNavigate();
    const [propertyDetail, setPropertyDetail] = useState({});


    useEffect(
        () => {
            console.log(params.id)
            if (params.id) {
                axios.get('http://localhost:8080/api/v1/properties/' + params.id )
                    .then(response => {
                        setPropertyDetail(response.data)
                    })
                    .catch(err => console.log(err.message))
            }
        }, [params.id])

    const deleteButtonClicked = (id) => {
        axios.delete('http://localhost:8080/api/v1/properties/' + id)
            .then(response => {
                // fetchProducts();
                navigate('/')
            })
            .catch(err => {
                console.error(err);
            })
    }


    const space = <Fragment>&nbsp;&nbsp;</Fragment>;

    let propertyDetailsDisplay = null;
    if (params.id) {

        propertyDetailsDisplay = (

            <div className="ProductDetail">
                <div>
                    property Details
                </div>
                <h1> {propertyDetail.title}</h1>
                <h1> {propertyDetail.price}</h1>
                <h1> {propertyDetail.image }</h1>
                <h1> {propertyDetail.number_of_bed_rooms}</h1>
                <h1> {propertyDetail.number_of_bath_rooms}</h1>
                <div >
                    {propertyDetail.description}
                    <br />
{/* 
                    <div style={{ textAlign: "left" }}>
                        {space} Reviews <br />
                        {productDetail.reviews != null ? productDetail.reviews.map(review => {
                            return <Review comment={review.comment} key={review.id}/>
                        }) : null}
                    </div> */}
                </div>
                <input
                    type="button"
                    value="Delete"
                    onClick={()=> { deleteButtonClicked(params.id) }} />
            </div>
        );
    }

    return propertyDetailsDisplay

}

export default PropertyDetail;