# SauceDemo Testing Checklist  

## Аутентификация <https://www.saucedemo.com/>

- [X] Вход с валидными данными (`standard_user / secret_sauce`)
- [X] Попытка входа без логина (Проверка сообщения об ошибке)
- [X] Попытка входа без пароля (Проверка сообщения об ошибке)
- [X] Попытка входа при несуществующем логине и пароле (Проверка сообщения об ошибке)
- [ ] Вход с заблокированным пользователем (`locked_out_user`)
- [ ] Проверить вход с проблемным пользователем (`problem_user`)
- [ ] Проверить вход с медленным пользователем (`performance_glitch_user`)
--- 
## Каталог товаров (Inventory Page) <https://www.saucedemo.com/inventory.html>

- [ ] Отображение всех 6 товаров
- [ ] Проверка сортировки:
    - [ ] Name (A to Z)
    - [ ] Name (Z to A)
    - [ ] Price (low to high)
    - [ ] Price (high to low)
- [ ] Переход на страницу отдельного товара
- [ ] Добавление/удаление товаров в корзину
- [ ] Проверка обновления счётчика корзины
---
## Страница товара (Item Details) <https://www.saucedemo.com/inventory-item.html?id=4>

- [ ] Отображение названия, описания и цены товара
- [ ] Кнопка "Back to products"
- [ ] Добавление товара в корзину со страницы товара
---

## Корзина (Cart Page) <https://www.saucedemo.com/cart.html>

- [X] Отображение всех добавленных товаров
- [X] Проверка пустоты корзины 
- [X] Удаление товара из корзины
- [X] Проверка кнопок:
    - [X] Continue Shopping
    - [X] Checkout
---
## Оформление заказа (Checkout)

### Step One:  Information User <https://www.saucedemo.com/checkout-step-one.html>

- [ ] Валидация обязательных полей (First Name, Last Name, Zip)
- [ ] Переход к Step Two

### Step Two: Overview <https://www.saucedemo.com/checkout-step-one.html>

- [ ] Итоговая сумма заказа и налоги
- [ ] Соответствие товаров и цен
- [ ] Проверка кнопок:
    - [ ] Finish
    - [ ] Cancel

### Step Three: Complete <https://www.saucedemo.com/checkout-complete.html>

- [ ] Сообщение об успешном оформлении заказа
- [ ] Кнопка "Back Home" возвращает на главную

---
## Негативные сценарии

- [ ] Переход по прямой ссылке на `inventory.html` без авторизации
- [ ] Попытка оформления заказа с пустой корзиной
