	$(document).ready(function () {
		// Handler for opening the edit modal and populating it with user data
		$('.edit-user').click(function () {
			const userId = $(this).data('user-id');

			$.ajax({
				url: `/admin/manageusers/user/${userId}`,
				type: 'GET',
				success: function (user) {
					$('#editUserId').val(user.id);
					$('#editUsername').val(user.username);
					$('#editFirstName').val(user.firstName);
					$('#editLastName').val(user.lastName);
					$('#editUserModal').modal('show');
				},
				error: function (error) {
					console.error('Error fetching user details:', error.responseText);
				}
			});
		});

		// Handler for submitting the edit form
		$('#editUserForm').submit(function (event) {
			event.preventDefault();
			const formData = $(this).serialize();

			$.ajax({
				url: `/admin/manageusers/edit`,
				type: 'POST',
				data: formData,
				beforeSend: function (xhr) {
					const token = $('input[name="_csrf"]').val();
					if (token) {
						xhr.setRequestHeader('X-CSRF-Token', token);
					}
				},
				success: function () {
					window.location.reload();
				},
				error: function (xhr) {
					console.error('Error updating user:', xhr.responseText);
				}
			});
		});

		// Handler for delete confirmation
		$('.delete-user').click(function (e) {
			e.preventDefault();
			const userId = $(this).data('user-id');

			console.log(userId);

			// Show delete confirmation modal
			$('#deleteUserModal').modal('show');

			// Dynamic binding for delete confirmation to avoid multiple bindings
			$('#deleteUserConfirm').off('click').on('click', function () {
				$.ajax({
					url: `/admin/manageusers/delete/${userId}`,
					type: 'POST',
					beforeSend: function (xhr) {
						const token = $('input[name="_csrf"]').val();
						if (token) {
							xhr.setRequestHeader('X-CSRF-Token', token);
						}
					},
					success: function () {
						window.location.href = "/admin/manageusers";
					},
					error: function (error) {
						console.error('Error deleting user:', error.responseText);
					}
				});
			});
		});
	});

	document.addEventListener('DOMContentLoaded', function () {
		const searchBar = document.getElementById('searchBar');
		searchBar.addEventListener('keyup', function (event) {
			const searchText = event.target.value.toLowerCase();
			const rows = document.querySelectorAll('tbody tr');

			rows.forEach(row => {
				const username = row.cells[0].textContent.toLowerCase(); 
				const name = row.cells[1].textContent.toLowerCase(); 
				if (username.includes(searchText) || name.includes(searchText)) {
					row.style.display = ''; 
				} else {
					row.style.display = 'none'; 
				}
			});
		});
	});