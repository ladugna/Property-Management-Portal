import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'


// just to check how to fetch from database

const FetchData = () => {
  const [data, setdata]=useState([])
        useEffect(()=>{
            axios.get('http://localhost:8080/api/v1/tests')
            .then(res=>setdata(res.data))
            .catch(err=>console.log(err));
        }, [])
    
  return (
    <div>FetchData</div>
  )
}

export default FetchData