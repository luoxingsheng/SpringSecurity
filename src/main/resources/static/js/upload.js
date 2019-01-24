function preImg() {
    var file = document.getElementById('img')
    var img = document.getElementById('showImg')
    var url = window.URL.createObjectURL(file.files[0])
    img.src = url
}