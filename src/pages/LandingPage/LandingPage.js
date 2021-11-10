import "./LandingPage.css";
import Header from "../../components/Header/Header";
import { Link } from "react-router-dom";
import ValidateSelect from "../../components/ValidateSelect/ValidateSelect";
import ValidateInput from "../../components/ValidateInput/ValidateInput";
import Button from "../../components/Button/Button";
import Carousel from "react-bootstrap/Carousel";
import img1 from "../../shared/assets/img/school1.jpg";
import img2 from "../../shared/assets/img/school2.jpg";
import img3 from "../../shared/assets/img/school3.jpg";
const LandingPage = () => {
  return (
    <>
      <Header name="" isLoggedIn={false}>
        <div className="nav-item-header">
          <b>Cá nhân</b>
          <div className="dropdown-content">
            <Link>Thông tin cá nhân</Link>
            <Link>Điểm thi</Link>
            <Link>Đổi mật khẩu</Link>
          </div>
        </div>
        <div className="nav-item-header">
          <b>Yêu cầu</b>
          <div className="dropdown-content">
            <Link>Yêu cầu nhà trường</Link>
            <Link>Yêu cầu nhà trường</Link>
            <Link>Yêu cầu nhà trường</Link>
          </div>
        </div>
        <div className="nav-item-header">
          <b>Khác</b>
          <div className="dropdown-content">
            <Link>Chức năng 1</Link>
            <Link>Chức năng 2</Link>
            <Link>Chức năng 3</Link>
          </div>
        </div>
      </Header>
      <Carousel>
        <Carousel.Item interval={1000}>
          <img className="d-block w-100" src={img1} alt="First slide" />
        </Carousel.Item>
        <Carousel.Item interval={1000}>
          <img className="d-block w-100" src={img2} alt="Second slide" />
        </Carousel.Item>
        <Carousel.Item interval={1000}>
          <img className="d-block w-100" src={img3} alt="Third slide" />
        </Carousel.Item>
      </Carousel>
      <div className="search-container" style={{ marginTop: "50px" }}>
        <ValidateSelect name="major" lable="Ngành">
          <option className="default-option" value="">
            -Chọn ngành-
          </option>
          <option value="Công nghệ thông tin">Công nghệ thông tin</option>
          <option value="Công nghệ sinh học">Công nghệ sinh học</option>
          <option value="Kiến trúc">Kiến trúc</option>
          <option value="Nhiệt điện">Nhiệt điện</option>
        </ValidateSelect>
        <ValidateSelect name="class" lable="Lớp">
          <option className="default-option" value="">
            -Chọn lớp-
          </option>
          <option value="Công nghệ thông tin">Công nghệ thông tin</option>
          <option value="Công nghệ sinh học">Công nghệ sinh học</option>
          <option value="Kiến trúc">Kiến trúc</option>
          <option value="Nhiệt điện">Nhiệt điện</option>
        </ValidateSelect>
        <ValidateInput name="teacher-name" lable="Tên" />
        <Button title="Tìm" />
      </div>
      <div className ="announcement">
          <h3 style ={{textDecoration:"underline"}}> Thông báo </h3>
      </div>
    </>
  );
};
export default LandingPage;
