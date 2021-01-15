## 크롭받은 이미지를 multipart로 만들어주자 (okhttp 4 버전)

```
                var resultUri = result.getUri()
                val options = BitmapFactory.Options()
                val inputStream = requireContext().contentResolver.openInputStream(resultUri)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                val photoBody =
                    RequestBody.create( //이전 okhttp3 버전에서 쓰였던 Media.Parse가 사용이 불가능해졌기때문에 toMediaTypeOrNull을 사용해야한다.
                        "image/jpg".toMediaTypeOrNull(),
                        byteArrayOutputStream.toByteArray()
                    )
                val part = MultipartBody.Part.createFormData(
                    "image",
                    File(resultUri.toString()).name,
                    photoBody
                )
                mypageViewModel.putProfiles(part)
```

위에서 이어져서 다음과같이 멀티파트를 만들어서 보내면 이걸통해 서버와 통신가능
다른 추가적인 string 같은 body가 필요하다면 추가적인 multipart-form data로 보내야함
