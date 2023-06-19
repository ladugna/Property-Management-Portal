import React from 'react';

const Property = ({ property }) => {
  return (
    <div className="property">
      <img src={property.image} alt="Property" />
      <h2>{property.address}</h2>
      <p>Price: {property.price}</p>
    </div>
  );
};

export default Property;
