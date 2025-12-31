(defun sum-all (lst)
  (cond
    ((null lst) 0)
    ((listp (car lst))
     (+ (sum-all (car lst))
        (sum-all (cdr lst))))
    ((numberp (car lst))
     (+ (car lst)
        (sum-all (cdr lst))))
    (t
     (sum-all (cdr lst)))))



(print (sum-all '(a 2 b (3 c 4 (d 6)) hello 8)))