import React from 'react'
import Header from '../Container/Header/Header'
import Routing from '../Container/Routes/Routing'




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