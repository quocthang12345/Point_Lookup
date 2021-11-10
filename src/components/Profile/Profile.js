import React from "react"
import "./Profile.css"
import defaultAvatar from "../../shared/assets/img/avatar.png"
import ProfileInput from "../ProfileInput/ProfileInput"
import Button from "../Button/Button"
import axios from "axios"
import { useEffect, useState } from "react"

const Profile = (props) => {
    const [avatar, setAvatar] = useState(defaultAvatar)
    // const [profileData, setProfileData] = useState({})
    // useEffect(() =>{
    //     axios.get("http://localhost:3001/posts")
    //         .then(response => {setProfileData(response.data);})
    // },[])
    // console.log(profileData[0])
    const handleChangeAvatar = (e) => {
        e.target.closest('.profile-avatar-container').querySelector('.avatar-file').click();
    }
    const handleApplyAvatar = (e) => {
        const file = e.target.files[0]
        file.preview = URL.createObjectURL(file);
        setAvatar(file)
    }
    useEffect(() => {
        return () => {
            URL.revokeObjectURL(avatar.preview)
        }
    },[avatar])
    
    return(
       <div className = "profile-container">
           <h2 className = "profile-header-title">{props.title || "Thông tin sinh viên"}</h2>
           <div className = "profile-detail-container">
               <div className = "profile-avatar-container" onClick = {handleChangeAvatar }>
                    <img className = "profile-avatar" src = {avatar.preview || avatar } alt = {avatar.preview || avatar}/>
                    <input type="file" className = "avatar-file" name="avatar-img" id="file" style={{display:"none"}} onChange={handleApplyAvatar}/>
                </div>
                <div className = "profile-detail-info">
                    <ProfileInput title = "- Họ và tên -  " disabled ={true} value ="Trần Phước Thịnh"/>
                    <ProfileInput title = "- Nơi ở hiện tại -   " disabled ={true} value ="Đà Nẵng"/>
                    <ProfileInput title = "- Ngày sinh -   " disabled ={true} value ="19-9-2000"/>
                    <ProfileInput title = "- Số CMND -    " disabled ={true} value ="123456789"/>
                    <ProfileInput title = "- Quốc tịch - " disabled ={true} value ="Việt Nam"/>
                    <ProfileInput title = "- Dân tộc - " disabled ={true} value ="Kinh"/>
                </div>
           </div>
           <div className = "study-detail-info" >
                <ProfileInput title = "- Email -  " inputClass = "study-info-item" disabled ={true} value ="thinh@gmail.com"/>
                <ProfileInput title = "- SDT - " inputClass = "study-info-item" disabled ={true} value ="123456789"/>
                {props.children}
            </div>
            <div style = {{display: "flex", justifyContent: "center"}}>
                <Button title = "Lưu" className ="save-profile-btn"/>
            </div>
       </div>
    )
}
export default Profile;