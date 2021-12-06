import "../ManageMajor/ManageMajor.css";
import Table from "react-bootstrap/Table";
import ValidateInput from "../../../components/ValidateInput/ValidateInput";
import Button from "../../../components/Button/Button";
import Header from "../../../components/Header/Header";
import { useState, useEffect, useRef } from "react";
import { Link, useLocation } from "react-router-dom";
import axios from "axios";
const ManageClass = () => {
  const [classes, setClasses] = useState([]);
  const location = useLocation();
  const allClasses = useRef([]);
  useEffect(() => {
    axios
      .get(
        "http://127.0.0.1:8080/api/findClassByMajor?majorCode=" +
          location.state.params.majorCode
      )
      .then((response) => {
        console.log(response.data.data);
        setClasses(response.data.data);
        allClasses.current = response.data.data;
      })
      .catch((error) => {
        console.log(error, "myError");
      });
  }, []);

  const handleSearchMajor = () => {
    const searchInput = document.querySelector(".form-control").value;
    if (searchInput) {
      setClasses((pre) =>
        pre.filter((item) => item.className.includes(searchInput))
      );
    } else {
      setClasses(allClasses.current);
    }
  };

  return (
    <>
      <Header
        isLoggedIn={true}
        name={JSON.parse(localStorage.getItem("user")).fullName}
      >
        <div className="nav-item-header">
          <b>Cá nhân</b>
          <div className="dropdown-content">
            <Link to="/adminprofile">Thông tin cá nhân</Link>
            <Link to="/changepassword">Đổi mật khẩu</Link>
          </div>
        </div>
        <div className="nav-item-header">
          <b>Quản lí tài khoản</b>
          <div className="dropdown-content">
            <Link to="/manageteacheraccount">Tài khoản giáo viên</Link>
            <Link to="/managestudentaccount">Tài khoản học sinh</Link>
          </div>
        </div>
        <div className="nav-item-header">
          <b>Quản lí học tập</b>
          <div className="dropdown-content">
            <Link to="managemajor">Quản lí lớp, khoa</Link>
            <Link to = "/subjectadmin">Quản lí môn học</Link>
          </div>
        </div>
      </Header>
      <div className="manage-account-page" style={{ marginTop: "20px" }}>
        <h2 style={{ margin: " 10px 20px", borderBottom: "solid 1px #111" }}>
          {location.state.params.majorName}
        </h2>
        <div className="search-container" style={{ marginLeft: "40px" }}>
          <ValidateInput
            name="class-search"
            lable="Tên Lớp"
            dropdownList={classes && classes.reduce(
              (value, item) => value.concat(item.className),
              []
            )}
          />
          <Button title="Dữ liệu" onClick={handleSearchMajor} />
        </div>
        <div className="manage-account">
          <h5>Quản lý lớp</h5>
          <Table responsive bordered hover>
            <thead>
              <tr>
                <th>Mã Lớp</th>
                <th>Tên lớp</th>
                <th>Chi tiết</th>
                <th>Xóa, sửa</th>
              </tr>
            </thead>
            <tbody>
              {classes &&
                classes.map((item, index) => (
                  <tr key={index}>
                    <th>{item.classCode}</th>
                    <td>{item.className}</td>
                    <td>
                      <p className="view-classes">Chi tiết lớp</p>
                    </td>
                    <td className="edit-major">
                      <Button title="Xóa" />
                      <Button title="Sửa" />
                    </td>
                  </tr>
                ))}
            </tbody>
          </Table>
          <div className="add-major">
            <Button title="Thêm lớp" />
          </div>
        </div>
      </div>
    </>
  );
};
export default ManageClass;
