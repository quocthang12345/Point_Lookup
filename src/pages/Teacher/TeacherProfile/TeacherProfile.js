import { Link } from "react-router-dom";
import ProfileInput from "../../../components/ProfileInput/ProfileInput";
import Header from "../../../components/Header/Header";
import Profile from "../../../components/Profile/Profile";
const TeacherProfile = () => {
  return (
    <>
      <Header isLoggedIn={true} name="tên giáo viên">
        <div className="nav-item-header">
          <b>Cá nhân</b>
          <div className="dropdown-content">
            <Link>Thông tin cá nhân</Link>
            <Link to="/changepassword">Đổi mật khẩu</Link>
          </div>
        </div>
        <div className="nav-item-header">
          <b>Quản lí điểm</b>
          <div className="dropdown-content">
            <Link>Yêu cầu nhà trường</Link>
            <Link to = "/scoreteacher">Quản lí điểm học sinh</Link>
          </div>
        </div>
        <div className="nav-item-header">
          <b>Khác</b>
          <div className="dropdown-content">
            <a href="#">Dropdown 1</a>
            <a href="#">Dropdown 2</a>
            <a href="#">Dropdown 3</a>
          </div>
        </div>
      </Header>
      <Profile title = "Thông tin giáo viên ">
        <ProfileInput title="- Khoa -" inputClass="study-info-item" />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <ProfileInput title="-Chức vụ- " inputClass="study-info-item" />
      </Profile>
    </>
  );
};

export default TeacherProfile;
