import React from 'react'
import Routing from '../Components/Routes/Routing'
import Header from '../Container/Header/Header'




const DashBoard = () => {
  return (
    <React.Fragment>   
    <div className='header'>
        <Header/>
    </div>
    <div className="Page">
  <Routing/>
    </div>
</React.Fragment>
  )
}

export default DashBoard