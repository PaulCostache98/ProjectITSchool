# PC Store

This project aims to simulate a PC Store website.

## Description

Through simple HTML and Thymeleaf implementation, users are able to explore a working simulation of a real web shop.
They can browse, add to cart, check out, inspect their account details and history, and even delete their accounts.

Admins, on the other hand, can edit, remove and add users and do the same with entities.

## Getting Started

### Dependencies

Java, Spring, Maven, Thymeleaf, CSS, JavaScript, HTML
Visit the project's pom.xml for an easy source of dependencies.

### Exploring the program

The website is coded in such a way to be intuitive, however here are some pointers:

Upon first entering the page you'll reach the index.
From here, you can view the Home, About and Shop pages.
Home will lead you back to the index.
About will show a summary of the project.

Shop will open the actual webshop.

Upon entering the Shop page, you will see a collection of towers for sale.
You can click on any to view more information and possibly buy them.

To buy anything, you will need to login.
If you do not have an account, the Register button is where you should go.
The register form is straight forward. It will send an email to your provided one after successfully filling the form, which you'll have to confirm.
The email will redirect you to an activation page, after which you'll be redirected to the login page.

If logged in, you will largely see the same, and now be able to purchase.

Upon purchase, the item will be added to your cart. You can now check your Cart at the top of the page, which will show you the items and offer a Checkout option.

If you do Checkout, you will go through a simple form and clear your cart. That cart will now appear in your purchase history as paid.

The Account page will also be accessible. This page will show you your Account Summary, your Purchase History, and an option to Delete your account.

These are all self explanatory.

That would be it for an User.


As an Admin, you will be given access to the Admin Page.

On this page, you'll be able to access information about all Users and all Towers.

You may edit or delete users(assuming they're not fellow admins), as well as Add a new User.

You may also edit, delete and add Towers.


## Authors

Paul Costache, @PaulC98
