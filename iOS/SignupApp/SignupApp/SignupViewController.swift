//
//  ViewController.swift
//  SignupApp
//
//  Created by kimdo2297 on 2020/03/24.
//  Copyright © 2020 Jason. All rights reserved.
//

import UIKit

class SignupViewController: UIViewController, NextButtonDelegate {
    @IBOutlet weak var idTextField: IDTextField!
    @IBOutlet weak var pwTextField: PWTextField!
    @IBOutlet weak var pwAgainTextField: PWAgainTextField!
    @IBOutlet weak var nameTextField: NameTextField!
    @IBOutlet weak var nextButton: NextButton!
    private lazy var textFields = [idTextField, pwTextField, pwAgainTextField, nameTextField]
    
    private let idTextFieldDelegate = IDTextFieldDelegate()
    private let pwAgainTextFieldDelegate = PWAgainTextFieldDelegate()
    private let nameTextFieldDelegate = NameTextFieldDelegate()
    private let pwTextFieldDelegate = PWTextFieldDelegate()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setDelegates()
        setNextResponders()
        setObserver()
    }
    
    private func setDelegates() {
        idTextField.delegate = idTextFieldDelegate
        pwTextField.delegate = pwTextFieldDelegate
        pwAgainTextField.delegate = pwAgainTextFieldDelegate
        nameTextField.delegate = nameTextFieldDelegate
        nextButton.delegate = self
    }
    
    private func setNextResponders() {
        idTextField.nextResonder = pwTextField
        pwTextField.nextResonder = pwAgainTextField
        pwAgainTextField.nextResonder = nameTextField
        nameTextField.nextResonder = nextButton
    }
    
    private func setObserver() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(changeNextButton),
                                               name: SignupTextField.notificationIsCorrectDidChange,
                                               object: nil)
    }
    
    @objc func changeNextButton() {
        if isAllCorrect() {
            nextButton.enabled()
        } else {
            nextButton.disabled()
        }
    }
    
    private func isAllCorrect() -> Bool {
        for textField in textFields {
            guard let textField = textField else { return false }
            if !textField.isCorrect {
                return false
            }
        }
        return true
    }
    
    func nextButtonBecomeFirstResponder() {
        actionNextButton()
    }
    
    private func actionNextButton() {
        if isAllCorrect() {
            performSegue(withIdentifier: "showLoginViewController", sender: nextButton)
        } else {
            invalidTextFieldBecomeFirstResponder()
        }
    }
    
    private func invalidTextFieldBecomeFirstResponder() {
        for textField in textFields {
            guard let textField = textField else { return }
            if !textField.isCorrect {
                textField.becomeFirstResponder()
                return
            }
        }
    }
}

