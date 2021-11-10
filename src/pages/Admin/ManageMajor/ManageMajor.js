import "./ManageMajor.css";
import Table from "react-bootstrap/Table";
import ValidateInput from "../../../components/ValidateInput/ValidateInput";
import Button from "../../../components/Button/Button";
import Header from "../../../components/Header/Header";
import Modal from "react-bootstrap/Modal";
import { useState, useEffect } from "react";
import axios from "axios";
import { Link, useLocation, useHistory } from "react-router-dom";
const ManageMajor = () => {
  const [modalDel, setModalDel] = useState(false);
  const [modalAdd, setModalAdd] = useState(false);
  const [majors, setMajors] = useState([]);
  const [delMajor, setDelMajor] = useState({});
  const history = useHistory();
  const handleViewClasses = (classInfo) => {
    history.push("/manageclass", { params: classInfo });
  };

  useEffect(() => {
    axios.get("http://127.0.0.1:8080/api/findMajor").then((response) => {
      // console.log(response.data.data);
      setMajors(response.data.data)
    });
  }, []);
  const handleDelMajor = (majorCode) => {
    console.log(majorCode);
    axios
      .delete("http://localhost:8080/api/DeleteMajorByCode", {
        majorCode: majorCode,
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((err) => {
        console.log("Lỗi", err);
      });
    setModalDel(false);
  };

  const handleAddlMajor = () => {
    const addMajorData = document.querySelectorAll(".add-major-data");
    console.log({
      majorCode: addMajorData[0].innerText,
      majorName: addMajorData[1].innerText,
    });
    axios
      .post("http://localhost:8080/api/addMajor", {
        majorCode: addMajorData[0].innerText,
        majorName: addMajorData[1].innerText,
      })
      .then((response) => console.log(response.data))
      .catch((err) => console.log(err));
    setModalAdd(false);
  };

  return (
    <>
      <Header name="admin Thịnh" isLoggedIn={true}>
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
            <Link>Quản lí lớp, khoa</Link>
            <Link>Quản lí điểm</Link>
          </div>
        </div>
      </Header>
      <div className="manage-account-page" style={{ marginTop: "20px" }}>
        <h2 style={{ margin: " 10px 20px", borderBottom: "solid 1px #111" }}>
          Trang quản lí khoa
        </h2>
        <div className="search-container" style={{ marginLeft: "40px" }}>
          <ValidateInput
            name="teacher-name"
            lable="Tên khoa"
            dropdownList={majors.reduce(
              (value, item) => value.concat(item.majorName),
              []
            )}
          />
          <Button title="Dữ liệu" />
        </div>
        <div className="manage-account">
          <h5>Quản lý khoa</h5>
          <Table responsive bordered hover>
            <thead>
              <tr>
                <th>Mã khoa</th>
                <th>Tên khoa</th>
                <th>Các lớp</th>
                <th>Xóa, sửa</th>
              </tr>
            </thead>
            <tbody>
              {majors.map((item, index) => (
                <tr key={index}>
                  <th>{item.majorCode}</th>
                  <td>{item.majorName}</td>
                  <td>
                    <p
                      className="view-classes"
                      onClick={() => handleViewClasses(item)}
                    >
                      Xem các lớp
                    </p>
                  </td>
                  <td className="edit-major">
                    <Button
                      title="Xóa"
                      onClick={() => {
                        setModalDel(true);
                        setDelMajor(item);
                      }}
                    />
                    <Button title="Sửa" />
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <div className="add-major">
            <Button
              title="Thêm khoa"
              onClick={() => {
                setModalAdd(true);
              }}
            />
          </div>
        </div>
      </div>
      {/* Delete Modal */}
      <Modal
        size="md"
        show={modalDel}
        onHide={() => setModalDel(false)}
        aria-labelledby="example-modal-sizes-title-lg"
      >
        <Modal.Header closeButton>
          <Modal.Title id="example-modal-sizes-title-lg">
            Xác nhận xóa khoa
          </Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ textAlign: "center", fontSize: "large" }}>
          {delMajor.majorCode} - {delMajor.majorName}
        </Modal.Body>
        <Modal.Footer>
          <Button
            onClick={() => handleDelMajor(delMajor.majorCode)}
            title="Xác nhận"
          />
        </Modal.Footer>
      </Modal>
      {/* Add Modal */}
      <Modal
        size="md"
        show={modalAdd}
        onHide={() => setModalAdd(false)}
        aria-labelledby="example-modal-sizes-title-lg"
      >
        <Modal.Header closeButton>
          <Modal.Title id="example-modal-sizes-title-lg">Thêm khoa</Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ textAlign: "center", fontSize: "large" }}>
          <Table responsive bordered hover>
            <thead>
              <tr>
                <th>Mã khoa</th>
                <th>Tên khoa</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th contentEditable={true} className="add-major-data"></th>
                <th contentEditable={true} className="add-major-data"></th>
              </tr>
            </tbody>
          </Table>
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={() => handleAddlMajor()} title="Xác nhận" />
        </Modal.Footer>
      </Modal>
      {/* Update Modal */}
      {/* <Modal size="md" show={modalUpdate} onHide ={() => setModalUpdate(false)} aria-labelledby="example-modal-sizes-title-lg">
          <Modal.Header closeButton>
              <Modal.Title id="example-modal-sizes-title-lg">Thêm khoa</Modal.Title>
          </Modal.Header>
          <Modal.Body style = {{textAlign: 'center', fontSize: "large"}}>
          <Table responsive bordered hover>
            <thead>
              <tr>
                <th>Mã khoa</th>
                <th>Tên khoa</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th contentEditable = {true} className = "add-major-data"></th>
                <th  contentEditable = {true} className = "add-major-data"></th>
              </tr>
            </tbody>
          </Table>
          </Modal.Body>
          <Modal.Footer>
              <Button onClick={() => handleAddlMajor()} title = "Xác nhận"/>
          </Modal.Footer>
      </Modal> */}
    </>
  );
};
export default ManageMajor;
