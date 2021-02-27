/// <reference types="Cypress" />

describe('OFD Catalog tests', () => {
    var items = ['Фискальный накопитель',
        'Без фискального накопителя и ОФД',
        'Фискальный накопитель 36 месяцев',
        'Фискальный накопитель 15 месяцев и ОФД 15 месяцев',
        'Фискальный накопитель 36 месяцев и ОФД 15 месяцев',
        'Фискальный накопитель 36 месяцев и ОФД 36 месяцев',
        'Фискальный накопитель и ОФД 15 месяцев']

    before(function () {
        cy.visit('/')
    })

    it('Validates all complectation checkboxes', () => {
        //находим список и валидируем варианты из списка с массивом выше с помощью jquery
        cy.get('.filter_show-item > :nth-child(2) > .filter-item-content > ul')
            .find('li').should('have.length', items.length)
            .each(($li, index) => {
                cy.get($li).invoke('text').then($text => {
                    expect($text).to.eq(items[index])
                })
            })
    })

    it('Filters by fiscal storage device', () => {
        cy.server()
        cy.route('GET', '/collection/all?options[1713815][]=15607579').as('storageDevices')

        cy.get('.filter_show-item > :nth-child(2) > .filter-item-content > .filter-values > :nth-child(1) > .filter-value-label').click()
        //ожидаем по ответу на запрос и по смене урла, а заодно добавляем проверки
        cy.wait('@storageDevices').then((response) => {
            expect(response.status).to.eq(200)
        })
        cy.url().should('include', '15607579')
        //проверяем, что регистратор у нас только 1 и валидаруем название и стоимость
        cy.get('.product_card').should('have.length', 1)
            .and('contain.text', 'Фискальный накопитель на 15 месяцев')
            .and('contain.text', '6 890 – 8 650')
    })
})