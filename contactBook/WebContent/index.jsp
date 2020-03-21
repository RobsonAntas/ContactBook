<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/Style.css" />
<meta charset="UTF-8">
<title>Login Area</title>
</head>
<body>
	<h1 class="first">Agenda de Contatos</h1>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div id="info"></div>
						<form id="valida">
							<div class="form-group">
								<label for="email">E-mail</label><input type="email" id="email"
									name="email" placeholder="e-mail" class="form-control"/>
							</div>
							<div class="form-group">
								<label for="password">Senha</label><input type="password"
									id="password" name="password" placeholder="senha"
									class="form-control"/>
							</div>
							<div class="form-group">
								<input type="submit" id="login" value="Acessar" 
									name="logica" class="btn btn-primary"/>
							<div id="error">
							</div>
							</div>
						</form>
								
					</div>
				</div>
			</div>
		</div>
	</div>
	<div>
	<p id="resultado"></p>
	</div>
<script type="text/javascript" src="js/teste.js"></script>
</body>
<body>
<p id="resultado"></p>
</body>
</html>