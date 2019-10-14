$(document).ready(function() {

    let btn = $('#clickButton')

    btn.on('click', function () {

        let categories = ""
        $('.categories:checked').each(function () {
            categories += $(this).data('name') + ", "
        })
        if (categories.length > 2) {
            categories = categories.substring(0, categories.length - 2)
        }
        if (categories == "") {
            categories = "?"
        }

        let bags = $('#bags').val()
        if (bags.length == 0) {
            bags = "?"
        }

        let institutions
        if ($('.institutions:checked').length) {
            institutions = $('.institutions:checked').data('name')
        } else {
            institutions = "?"
        }

        let street = $('#street').val()
        if (street.length == 0) {
            street = "Nieznana ulica..."
        }
        let city = $('#city').val()
        if (city.length == 0) {
            city = "Nieznane miasto..."
        }
        let zipCode = $('#zipCode').val()
        if (zipCode.length == 0) {
            zipCode = "Nieznany kod pocztowy..."
        }
        let phoneNumber = $('#phoneNumber').val()
        if (phoneNumber.length == 0) {
            phoneNumber = "Nieznany nr telefonu..."
        }
        let pickUpDate = $('#pickUpDate').val()
        if (pickUpDate.length == 0) {
            pickUpDate = "Nieznana data odbioru..."
        }
        let pickUpTime = $('#pickUpTime').val()
        if (pickUpTime.length == 0) {
            pickUpTime = "Nieznany czas odbioru..."
        }
        let pickUpComment = $('#pickUpComment').val()
        if (pickUpComment.length == 0) {
            pickUpComment = "Brak komentarza."
        }

        let categoriesBagsS = $('#sumBagsAndCategories')
        let institutionS = $('#sumInstitution')
        let streetS = $('#sumStreet')
        let cityS = $('#sumCity')
        let zipCodeS = $('#sumZipCode')
        let phoneNumberS = $('#sumPhoneNumber')
        let pickUpDateS = $('#sumPickUpDate')
        let pickUpTimeS = $('#sumPickUpTime')
        let pickUpCommentS = $('#sumPickUpComment')

        categoriesBagsS.html('Ilość worków: ' + bags + ', z zawartością: ' + categories)
        institutionS.html('Dla: ' + institutions)
        streetS.html(street)
        cityS.html(city)
        zipCodeS.html(zipCode)
        phoneNumberS.html(phoneNumber)
        pickUpDateS.html(pickUpDate)
        pickUpTimeS.html(pickUpTime)
        pickUpCommentS.html(pickUpComment)
    })

});