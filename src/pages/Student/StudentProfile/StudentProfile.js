import { Link } from "react-router-dom";
import ProfileInput  from "../../../components/ProfileInput/ProfileInput";
import Header from "../../../components/Header/Header"
import Profile from "../../../components/Profile/Profile";
const StudentProfile = () => {
    return (
        <>
        <Header>
        <div className = "nav-item-header"><b>Cá nhân</b>
                <div className = "dropdown-content">
                    <Link >Thông tin cá nhân</Link>
                    <Link to = '/scorestudent'>Điểm thi</Link>
                    <Link to ="/schedule">Lịch học, lịch thi</Link>
                    <Link to ="/changepassword">Đổi mật khẩu</Link>
                </div>
            </div>
            <div className = "nav-item-header"><b>Yêu cầu</b>
                <div className = "dropdown-content">
                    <Link >Yêu cầu nhà trường</Link>
                    <Link >Yêu cầu nhà trường</Link>
                    <Link >Yêu cầu nhà trường</Link>
                    
                </div>
            </div>
            <div className = "nav-item-header"><b>Khác</b>
                <div className = "dropdown-content">
                    <Link>Chức năng 1</Link>  
                    <Link>Chức năng 2</Link>
                    <Link>Chức năng 3</Link>
                </div>
            </div>
        </Header>
        <Profile> 
            <ProfileInput title = "- Khoa -" inputClass = "study-info-item"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <ProfileInput title = "-  Lớp  - " inputClass = "study-info-item"/>
        </Profile>
        </>
    )
}

export default StudentProfile