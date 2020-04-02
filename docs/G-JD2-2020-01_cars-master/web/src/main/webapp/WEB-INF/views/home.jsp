<div>home page</div>


<div>
	<form method="POST" enctype="multipart/form-data" action="${contextPath}/file">
		<table>
			<tr>
				<td>File to upload:</td>
				<td><input type="file" name="file" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Upload" /></td>
			</tr>
		</table>
	</form>

	<img alt="no image" src="${contextPath}/file/image?uuid=${param.uuid}" />
</div>


