<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="loader-wrapper">
        <div id="loader"></div>

        <div class="loader-section section-left"></div>
        <div class="loader-section section-right"></div>

    </div>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="index">
                <i class="fas fa-film mr-2"></i>
                TT ENTERTAIMENT
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto mb-2 mb-lg-0">
            	<c:choose>
            		<c:when test="${not empty sessionScope.currentUser}">
            			 <li class="nav-item">
                    		<a class="nav-link nav-link-3" data-toggle="modal" data-target="#changePassModal">Welcome ${sessionScope.currentUser.username}</a>
		                </li>
		                 <li class="nav-item">
		                    <a class="nav-link nav-link-3" href="favorites">My Favorites</a>
		                </li>
		                 <li class="nav-item">
		                    <a class="nav-link nav-link-4" href="history">History</a>
		                </li>
		                 <li class="nav-item">
		                    <a class="nav-link nav-link-4" href="logout">Logout</a>
		                </li>
		                <c:if test="${sessionScope.currentUser.isAdmin}">
							<li class="nav-item">
								<a class="nav-link nav-link-5" href="admin">Admin</a>
							</li>
						</c:if>
            		</c:when>
            		<c:otherwise>
            			<li class="nav-item">
                    		<a class="nav-link nav-link-2" href="login">Login</a>
		                </li>
		                <li class="nav-item">
		                    <a class="nav-link nav-link-4" href="register">Register</a>
		                </li>
		                 <li class="nav-item">
		                    <a class="nav-link nav-link-4" href="forgotPass">Forgot password</a>
		                </li>
            		</c:otherwise>
            	</c:choose>
            </ul>
            </div>
        </div>
    </nav>

    <div class="tm-hero d-flex justify-content-center align-items-center" data-parallax="scroll" data-image-src="<c:url value='templates/user/img/poster.jpg'/>">
        <form class="d-flex tm-search-form">
        </form>
    </div>
    
    <!-- Modal -->
  <div class="modal fade" id="changePassModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Chang Password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
			<input type="password" name="currentPass" id="currentPass" class="form-control rounded-0"
					placeholder="Current Password" required />
		</div>
		<div class="form-group">
			<input type="password" name="newPass" id="newPass" class="form-control rounded-0"
					placeholder="New Password" required />
		</div>
		<h5 style="color: red" id="messageChangePass"></h5>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-warning" id="changePassBtn">Submit</button>
      </div>
    </div>
  </div>
</div>
