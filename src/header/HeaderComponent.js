import React, { useState } from "react";
import "./headerCss.css"
import {BrowserRouter as Router, Link } from "react-router-dom";
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';


const HeaderComponent = () =>{
	const [dropdownOpen, setDropdown] = useState(false);
	function toggle() {
		setDropdown(prevState => ({
			dropdownOpen: !prevState.dropdownOpen
		}));
	  }
    return(
        <div>
        <header className="header-section">
		<div className="logo-container"> 
			
			<img src="img/logo.jpg" alt="" />
			<div className="spacer"></div>
			<div className="title">
				<h3 className="project-name">Hệ thống tra cứu & Quản lý điểm</h3>
				<h4 className="project-team">PBL6 - Nhóm 5</h4>
			</div>
			<br/>
			
			<br/>
					
		</div>	
		<div className="navbar">
			<div className="nav-group-items" style={{justifyContent:"space-evenly"}}>
				
				<div className="nav-item">
					<b>Cá nhân</b>
					<div className= "dropdown-content">
						<Link to="/scheldule"><a href="#">Lịch học</a></Link>
						<Link to="/score"><a href="#">Điểm thi</a></Link>
					</div>
				</div>
				<div className="nav-item">
					<b>Yêu cầu</b>
					<div className="dropdown-content">
						<a href="#">Yêu cầu nhà trường</a>
						<a href="#">Yêu cầu 2</a>
						<a href="#">Yêu cầu 3</a>
					</div>
				</div>
				<div className="nav-item">
					<b>Khác</b>
					<div className= "dropdown-content">
						<a href="#">Dropdown 1</a>
						<a href="#">Dropdown 2</a>
						<a href="#">Dropdown 3</a>
					</div>
				</div>
			</div>
			
			{/* <div className="nav-info">Trần Phước Thịnh</div> */}
			<div className= "nav-login">
				<Link to="/login"><div className= "login-btn"><b>Đăng Nhập</b>
					<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
						<path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
						<path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
					  </svg><i className="bi bi-box-arrow-in-right"></i></div>
				</Link>
			</div>
		</div>
		
	    </header>
        </div>
    )
}

export default HeaderComponent;