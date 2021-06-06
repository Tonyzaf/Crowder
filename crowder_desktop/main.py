import tkinter as tk
from tkinter import ttk
from tkinter import *
from PIL import ImageTk, Image
import sqlite3

root = Tk()

# Δημιουργία αρχικής οθόνης
style = ttk.Style(root)
root.tk.call('source', 'azure.tcl')
style.theme_use('azure')
root.title("Crowder")
root.iconbitmap("favicon.ico")
root.geometry("900x700")
root.option_add('*Font', "Arial")
logo = ImageTk.PhotoImage(Image.open("crowder.png"))
label = Label(image=logo)
label.pack()

def login():
    # Σύνδεση
    Label(root, text="Όνομα χρήστη:").pack()
    username = Entry(root, textvariable="username")
    username.pack()
    Label(root, text="").pack()
    Label(root, text="Κώδικός σύνδεσης:").pack()
    password = Entry(root, textvariable="password", show='*')
    password.pack()
    Label(root, text="").pack()
    # create Login Button
    button = Button(text="Είσοδος", height="2", width="30", bg='#FFA6F9', command=lambda: eisodos(root))
    button.pack()
    Label(root, text="").pack()
    Label(root, text="Δεν έχετε λογιαριασμό; Εγγραφείτε εδώ").pack()

def eisodos(root):
    root.destroy()
    import arxikh


def eggrafh():

    return
login()

#Database
conn = sqlite3.connect("address_book.db")
c = conn.cursor()
conn.commit()

#create table
c.execute("""DROP TABLE users """)
c.execute("""CREATE TABLE users(
        username text,
        email  text,
        password text)
        """"")


conn.close()
root.mainloop()
