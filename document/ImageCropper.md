# 이미지 크롭으로 이미지를 원하는 비율로 잘라봅시다

## 이미지 크롭 이벤트를 처리해주는 Image Cropper

```
implementation 'com.theartofdev.edmodo:android-image-cropper:2.8.0'
```
우선 implemetion을 해주고 여기서 제공해주는 Image Crop Activity를 manifest에 정의

```
<activity
            android:name="com.theartofdev.edmodo.cropper.CropImageActivity"
            android:screenOrientation="portrait"
            android:theme="@style/Base.Theme.AppCompat" />
```
그 후 image를 고르는 화면으로 넘어가는 intent를 시작하는 함수를 만듬

```
private fun ImagePicker() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("crop", "true")
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
}
```
이 후 선택한 이미지를 크롭하기 위해 startActivityForResult를 정의

```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            CropImage.activity(data.data!!).setAspectRatio(4, 3).start(requireContext(), this)  // 4대 3 비율로 지정, 안에서 정해진 비율안에 이미지를 조절할 수 있다
            //Fragment에서 사용하였기 때문에 requireContext(), this를 사용, Activity에서는 this만 쓰면된다.
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) { //이미지 크롭 완료 버튼을 누르면 데이터 처리,
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                var resultUri = result.getUri() ///해당 resultUri를 활용하여 이미지를 사용하면 됩니다.
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.getError()
                Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
            }
        }
}
```